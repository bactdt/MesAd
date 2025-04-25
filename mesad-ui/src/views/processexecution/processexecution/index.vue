<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工序编码" prop="processCode">
        <el-input
          v-model="queryParams.processCode"
          placeholder="请输入工序编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工序名称" prop="processName">
        <el-input
          v-model="queryParams.processName"
          placeholder="请输入工序名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工序状态" prop="processStatus">
        <el-select v-model="queryParams.processStatus" placeholder="请选择工序状态" clearable>
          <el-option
            v-for="dict in mes_production_execution_satus"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['processexecution:processexecution:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['processexecution:processexecution:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['processexecution:processexecution:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['processexecution:processexecution:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processexecutionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工序执行ID" align="center" prop="processExecutionId" />
      <el-table-column label="生产执行ID" align="center" prop="executionId" />
      <el-table-column label="工序ID" align="center" prop="processId" />
      <el-table-column label="工序编码" align="center" prop="processCode" />
      <el-table-column label="工序名称" align="center" prop="processName" />
      <el-table-column label="工位ID" align="center" prop="workstationId" />
      <el-table-column label="工位编码" align="center" prop="workstationCode" />
      <el-table-column label="工位名称" align="center" prop="workstationName" />
      <el-table-column label="操作工ID" align="center" prop="workerId" />
      <el-table-column label="操作工姓名" align="center" prop="workerName" />
      <el-table-column label="计划生产数" align="center" prop="planQuantity" />
      <el-table-column label="实际生产数" align="center" prop="actualQuantity" />
      <el-table-column label="合格数量" align="center" prop="qualifiedQuantity" />
      <el-table-column label="不合格数量" align="center" prop="unqualifiedQuantity" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="工序状态" align="center" prop="processStatus">
        <template #default="scope">
          <dict-tag :options="mes_production_execution_satus" :value="scope.row.processStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['processexecution:processexecution:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['processexecution:processexecution:remove']">删除</el-button>
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

    <!-- 添加或修改生产工序执行对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processexecutionRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="生产执行ID" prop="executionId">
          <el-input v-model="form.executionId" placeholder="请输入生产执行ID" />
        </el-form-item>
        <el-form-item label="工序ID" prop="processId">
          <el-input v-model="form.processId" placeholder="请输入工序ID" />
        </el-form-item>
        <el-form-item label="工序编码" prop="processCode">
          <el-input v-model="form.processCode" placeholder="请输入工序编码" />
        </el-form-item>
        <el-form-item label="工序名称" prop="processName">
          <el-input v-model="form.processName" placeholder="请输入工序名称" />
        </el-form-item>
        <el-form-item label="工位ID" prop="workstationId">
          <el-input v-model="form.workstationId" placeholder="请输入工位ID" />
        </el-form-item>
        <el-form-item label="工位编码" prop="workstationCode">
          <el-input v-model="form.workstationCode" placeholder="请输入工位编码" />
        </el-form-item>
        <el-form-item label="工位名称" prop="workstationName">
          <el-input v-model="form.workstationName" placeholder="请输入工位名称" />
        </el-form-item>
        <el-form-item label="操作工ID" prop="workerId">
          <el-input v-model="form.workerId" placeholder="请输入操作工ID" />
        </el-form-item>
        <el-form-item label="操作工姓名" prop="workerName">
          <el-input v-model="form.workerName" placeholder="请输入操作工姓名" />
        </el-form-item>
        <el-form-item label="计划生产数" prop="planQuantity">
          <el-input v-model="form.planQuantity" placeholder="请输入计划生产数" />
        </el-form-item>
        <el-form-item label="实际生产数" prop="actualQuantity">
          <el-input v-model="form.actualQuantity" placeholder="请输入实际生产数" />
        </el-form-item>
        <el-form-item label="合格数量" prop="qualifiedQuantity">
          <el-input v-model="form.qualifiedQuantity" placeholder="请输入合格数量" />
        </el-form-item>
        <el-form-item label="不合格数量" prop="unqualifiedQuantity">
          <el-input v-model="form.unqualifiedQuantity" placeholder="请输入不合格数量" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工序状态" prop="processStatus">
          <el-select v-model="form.processStatus" placeholder="请选择工序状态">
            <el-option
              v-for="dict in mes_production_execution_satus"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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

<script setup name="Processexecution">
import { listProcessexecution, getProcessexecution, delProcessexecution, addProcessexecution, updateProcessexecution } from "@/api/processexecution/processexecution";

const { proxy } = getCurrentInstance();
const { mes_production_execution_satus } = proxy.useDict('mes_production_execution_satus');

const processexecutionList = ref([]);
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
    processCode: null,
    processName: null,
    processStatus: null,
  },
  rules: {
    workstationId: [
      { required: true, message: "工位ID不能为空", trigger: "blur" }
    ],
    workstationCode: [
      { required: true, message: "工位编码不能为空", trigger: "blur" }
    ],
    workstationName: [
      { required: true, message: "工位名称不能为空", trigger: "blur" }
    ],
    workerId: [
      { required: true, message: "操作工ID不能为空", trigger: "blur" }
    ],
    workerName: [
      { required: true, message: "操作工姓名不能为空", trigger: "blur" }
    ],
    unit: [
      { required: true, message: "单位不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询生产工序执行列表 */
function getList() {
  loading.value = true;
  listProcessexecution(queryParams.value).then(response => {
    processexecutionList.value = response.rows;
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
    processExecutionId: null,
    executionId: null,
    processId: null,
    processCode: null,
    processName: null,
    workstationId: null,
    workstationCode: null,
    workstationName: null,
    workerId: null,
    workerName: null,
    planQuantity: null,
    actualQuantity: null,
    qualifiedQuantity: null,
    unqualifiedQuantity: null,
    unit: null,
    startTime: null,
    endTime: null,
    processStatus: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("processexecutionRef");
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
  ids.value = selection.map(item => item.processExecutionId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加生产工序执行";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _processExecutionId = row.processExecutionId || ids.value
  getProcessexecution(_processExecutionId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改生产工序执行";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processexecutionRef"].validate(valid => {
    if (valid) {
      if (form.value.processExecutionId != null) {
        updateProcessexecution(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessexecution(form.value).then(response => {
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
  const _processExecutionIds = row.processExecutionId || ids.value;
  proxy.$modal.confirm('是否确认删除生产工序执行编号为"' + _processExecutionIds + '"的数据项？').then(function() {
    return delProcessexecution(_processExecutionIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('processexecution/processexecution/export', {
    ...queryParams.value
  }, `processexecution_${new Date().getTime()}.xlsx`)
}

getList();
</script>
