<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="参数名称" prop="parameterName">
        <el-input
          v-model="queryParams.parameterName"
          placeholder="请输入参数名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上限" prop="upperLimit">
        <el-input
          v-model="queryParams.upperLimit"
          placeholder="请输入上限"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下限" prop="lowerLimit">
        <el-input
          v-model="queryParams.lowerLimit"
          placeholder="请输入下限"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="unit">
        <el-input
          v-model="queryParams.unit"
          placeholder="请输入单位"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['production_parameter:parameter:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['production_parameter:parameter:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['production_parameter:parameter:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['production_parameter:parameter:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="parameterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="参数ID" align="center" prop="parameterId" />
      <el-table-column label="参数名称" align="center" prop="parameterName" />
      <el-table-column label="参数编码" align="center" prop="parameterCode" />
      <el-table-column label="参数值" align="center" prop="parameterValue" />
      <el-table-column label="标准值" align="center" prop="standardValue" />
      <el-table-column label="上限" align="center" prop="upperLimit" />
      <el-table-column label="下限" align="center" prop="lowerLimit" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['production_parameter:parameter:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['production_parameter:parameter:remove']">删除</el-button>
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

    <!-- 添加或修改生产参数记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="parameterRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工序执行ID" prop="processExecutionId">
          <el-input v-model="form.processExecutionId" placeholder="请输入工序执行ID" />
        </el-form-item>
        <el-form-item label="参数名称" prop="parameterName">
          <el-input v-model="form.parameterName" placeholder="请输入参数名称" />
        </el-form-item>
        <el-form-item label="参数编码" prop="parameterCode">
          <el-input v-model="form.parameterCode" placeholder="请输入参数编码" />
        </el-form-item>
        <el-form-item label="参数值" prop="parameterValue">
          <el-input v-model="form.parameterValue" placeholder="请输入参数值" />
        </el-form-item>
        <el-form-item label="标准值" prop="standardValue">
          <el-input v-model="form.standardValue" placeholder="请输入标准值" />
        </el-form-item>
        <el-form-item label="上限" prop="upperLimit">
          <el-input v-model="form.upperLimit" placeholder="请输入上限" />
        </el-form-item>
        <el-form-item label="下限" prop="lowerLimit">
          <el-input v-model="form.lowerLimit" placeholder="请输入下限" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="是否合格" prop="isQualified">
          <el-input v-model="form.isQualified" placeholder="请输入是否合格" />
        </el-form-item>
        <el-form-item label="记录时间" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择记录时间">
          </el-date-picker>
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

<script setup name="Parameter">
import { listParameter, getParameter, delParameter, addParameter, updateParameter } from "@/api/production_parameter/parameter";

const { proxy } = getCurrentInstance();

const parameterList = ref([]);
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
    parameterName: null,
    upperLimit: null,
    lowerLimit: null,
    unit: null,
  },
  rules: {
    processExecutionId: [
      { required: true, message: "工序执行ID不能为空", trigger: "blur" }
    ],
    parameterName: [
      { required: true, message: "参数名称不能为空", trigger: "blur" }
    ],
    parameterCode: [
      { required: true, message: "参数编码不能为空", trigger: "blur" }
    ],
    parameterValue: [
      { required: true, message: "参数值不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询生产参数记录列表 */
function getList() {
  loading.value = true;
  listParameter(queryParams.value).then(response => {
    parameterList.value = response.rows;
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
    parameterId: null,
    processExecutionId: null,
    parameterName: null,
    parameterCode: null,
    parameterValue: null,
    standardValue: null,
    upperLimit: null,
    lowerLimit: null,
    unit: null,
    isQualified: null,
    recordTime: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("parameterRef");
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
  ids.value = selection.map(item => item.parameterId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加生产参数记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _parameterId = row.parameterId || ids.value
  getParameter(_parameterId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改生产参数记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["parameterRef"].validate(valid => {
    if (valid) {
      if (form.value.parameterId != null) {
        updateParameter(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addParameter(form.value).then(response => {
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
  const _parameterIds = row.parameterId || ids.value;
  proxy.$modal.confirm('是否确认删除生产参数记录编号为"' + _parameterIds + '"的数据项？').then(function() {
    return delParameter(_parameterIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('production_parameter/parameter/export', {
    ...queryParams.value
  }, `parameter_${new Date().getTime()}.xlsx`)
}

getList();
</script>
