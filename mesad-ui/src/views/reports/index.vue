<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="报表名称" prop="reportName">
        <el-input
          v-model="queryParams.reportName"
          placeholder="请输入报表名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报表类型" prop="reportType">
        <el-select v-model="queryParams.reportType" placeholder="请选择报表类型" clearable>
          <el-option label="日报" value="日报" />
          <el-option label="周报" value="周报" />
          <el-option label="月报" value="月报" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="正常" value="0" />
          <el-option label="停用" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['report:report:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate" v-hasPermi="['report:report:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete" v-hasPermi="['report:report:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['report:report:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报表ID" align="center" prop="reportId" />
      <el-table-column label="报表名称" align="center" prop="reportName" />
      <el-table-column label="报表类型" align="center" prop="reportType" />
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <el-tag :type="scope.row.status === '0' ? 'success' : 'danger'">{{ scope.row.status === '0' ? '正常' : '停用' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="160" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['report:report:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['report:report:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改生产报表对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="reportRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报表名称" prop="reportName">
          <el-input v-model="form.reportName" placeholder="请输入报表名称" />
        </el-form-item>
        <el-form-item label="报表类型" prop="reportType">
          <el-select v-model="form.reportType" placeholder="请选择报表类型">
            <el-option label="日报" value="日报" />
            <el-option label="周报" value="周报" />
            <el-option label="月报" value="月报" />
          </el-select>
        </el-form-item>
        <el-form-item label="报表内容" prop="reportContent">
          <el-input v-model="form.reportContent" type="textarea" :rows="4" placeholder="请输入报表内容" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio value="0">正常</el-radio>
            <el-radio value="1">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Reports">
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/report/report";

const { proxy } = getCurrentInstance();

const reportList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    reportName: null,
    reportType: null,
    status: null,
  },
  rules: {
    reportName: [
      { required: true, message: "报表名称不能为空", trigger: "blur" }
    ],
    reportType: [
      { required: true, message: "报表类型不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询生产报表列表 */
function getList() {
  loading.value = true;
  listReport(queryParams.value).then(response => {
    reportList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    reportId: null,
    reportName: null,
    reportType: null,
    reportContent: null,
    status: "0",
    remark: null,
  };
  proxy.resetForm("reportRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.reportId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加生产报表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _reportId = row.reportId || ids.value;
  getReport(_reportId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改生产报表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["reportRef"].validate(valid => {
    if (valid) {
      if (form.value.reportId != null) {
        updateReport(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addReport(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _reportIds = row.reportId || ids.value;
  proxy.$modal.confirm('是否确认删除报表编号为"' + _reportIds + '"的数据项？').then(function() {
    return delReport(_reportIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('report/report/export', {
    ...queryParams.value
  }, `report_${new Date().getTime()}.xlsx`)
}

getList();
</script>
