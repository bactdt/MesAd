<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderCode">
        <el-input
          v-model="queryParams.orderCode"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品编码" prop="productCode">
        <el-input
          v-model="queryParams.productCode"
          placeholder="请输入产品编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车间名称" prop="workshopName">
        <el-input
          v-model="queryParams.workshopName"
          placeholder="请输入车间名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产线名称" prop="lineName">
        <el-input
          v-model="queryParams.lineName"
          placeholder="请输入生产线名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="执行状态" prop="executionStatus">
        <el-select v-model="queryParams.executionStatus" placeholder="请选择执行状态" clearable>
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
          v-hasPermi="['productexecution:execution:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['productexecution:execution:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['productexecution:execution:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['productexecution:execution:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="executionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="执行ID" align="center" prop="executionId" />
      <el-table-column label="订单编号" align="center" prop="orderCode" />
      <el-table-column label="产品编码" align="center" prop="productCode" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="车间名称" align="center" prop="workshopName" />
      <el-table-column label="生产线名称" align="center" prop="lineName" />
      <el-table-column label="计划生产数量" align="center" prop="planQuantity" />
      <el-table-column label="实际生产数量" align="center" prop="actualQuantity" />
      <el-table-column label="合格数量" align="center" prop="qualifiedQuantity" />
      <el-table-column label="不合格数量" align="center" prop="unqualifiedQuantity" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="执行状态" align="center" prop="executionStatus">
        <template #default="scope">
          <el-radio-group
            v-model="scope.row.executionStatus"
            size="small"
            @change="val => handleStatusChange(scope.row, val)"
          >
            <el-radio-button
              v-for="dict in mes_production_execution_satus"
              :key="dict.value"
              :value="dict.value"
            >{{ dict.label }}</el-radio-button>
          </el-radio-group>
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

    <!-- 添加或修改生产执行对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="executionRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="排产ID" prop="scheduleId">
              <el-select v-model="form.scheduleId" placeholder="请选择排产ID" @change="handleScheduleChange" style="width: 100%">
                <el-option
                  v-for="item in scheduleOptions"
                  :key="item.scheduleId"
                  :label="item.scheduleId"
                  :value="item.scheduleId">
                  <span style="float: left">{{ item.scheduleId }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.orderCode }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="订单ID" prop="orderId">
              <el-input v-model="form.orderId" placeholder="请输入订单ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="订单编号" prop="orderCode">
              <el-input v-model="form.orderCode" placeholder="请输入订单编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品ID" prop="productId">
              <el-input v-model="form.productId" placeholder="请输入产品ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品编码" prop="productCode">
              <el-input v-model="form.productCode" placeholder="请输入产品编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品名称" prop="productName">
              <el-input v-model="form.productName" placeholder="请输入产品名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车间ID" prop="workshopId">
              <el-input v-model="form.workshopId" placeholder="请输入车间ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车间名称" prop="workshopName">
              <el-input v-model="form.workshopName" placeholder="请输入车间名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产线ID" prop="lineId">
              <el-input v-model="form.lineId" placeholder="请输入生产线ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产线名称" prop="lineName">
              <el-input v-model="form.lineName" placeholder="请输入生产线名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="计划生产数" prop="planQuantity">
              <el-input v-model="form.planQuantity" placeholder="请输入计划生产数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="实际生产数" prop="actualQuantity">
              <el-input v-model="form.actualQuantity" placeholder="请输入实际生产数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="合格数量" prop="qualifiedQuantity">
              <el-input v-model="form.qualifiedQuantity" placeholder="请输入合格数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="不合格数量" prop="unqualifiedQuantity">
              <el-input v-model="form.unqualifiedQuantity" placeholder="请输入不合格数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位" prop="unit">
              <el-input v-model="form.unit" placeholder="请输入单位" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker clearable
                v-model="form.startTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择开始时间"
                style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker clearable
                v-model="form.endTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择结束时间"
                style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="执行状态" prop="executionStatus">
              <el-select v-model="form.executionStatus" placeholder="请选择执行状态" style="width: 100%" >
                <el-option
                  v-for="dict in mes_production_execution_satus"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
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

<script setup name="Execution">
import { listExecution, getExecution, delExecution, addExecution, updateExecution } from "@/api/productexecution/execution";
import {listSchedule} from "@/api/productexecution/schedule";
import { now } from "@vueuse/core";
const { proxy } = getCurrentInstance();
const { mes_production_execution_satus } = proxy.useDict('mes_production_execution_satus');

const executionList = ref([]);
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
    orderCode: null,
    productCode: null,
    productName: null,
    workshopName: null,
    lineName: null,
    startTime: null,
    endTime: null,
    executionStatus: 0,
  },
  rules: {
    scheduleId: [
      { required: true, message: "排产ID不能为空", trigger: "blur" }
    ],
    orderId: [
      { required: true, message: "订单ID不能为空", trigger: "blur" }
    ],
    orderCode: [
      { required: true, message: "订单编号不能为空", trigger: "blur" }
    ],
    productId: [
      { required: true, message: "产品ID不能为空", trigger: "blur" }
    ],
    productCode: [
      { required: true, message: "产品编码不能为空", trigger: "blur" }
    ],
    productName: [
      { required: true, message: "产品名称不能为空", trigger: "blur" }
    ],
    workshopId: [
      { required: true, message: "车间ID不能为空", trigger: "blur" }
    ],
    workshopName: [
      { required: true, message: "车间名称不能为空", trigger: "blur" }
    ],
    lineId: [
      { required: true, message: "生产线ID不能为空", trigger: "blur" }
    ],
    lineName: [
      { required: true, message: "生产线名称不能为空", trigger: "blur" }
    ],
    planQuantity: [
      { required: true, message: "计划生产数量不能为空", trigger: "blur" }
    ],
    unit: [
      { required: true, message: "单位不能为空", trigger: "blur" }
    ],
    actualQuantity: [
      { required: true, message: "实际生产数量不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询生产执行列表 */
function getList() {
  loading.value = true;
  listExecution(queryParams.value).then(response => {
    executionList.value = response.rows;
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
    executionId: null,
    scheduleId: null,
    orderId: null,
    orderCode: null,
    productId: null,
    productCode: null,
    productName: null,
    workshopId: null,
    workshopName: null,
    lineId: null,
    lineName: null,
    planQuantity: null,
    actualQuantity: null,
    qualifiedQuantity: null,
    unqualifiedQuantity: null,
    unit: null,
    startTime: null,
    endTime: null,
    executionStatus: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("executionRef");
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
  ids.value = selection.map(item => item.executionId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加生产执行";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _executionId = row.executionId || ids.value
  getExecution(_executionId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改生产执行";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["executionRef"].validate(valid => {
    if (valid) {
      if (form.value.executionId != null) {
        updateExecution(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addExecution(form.value).then(response => {
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
  const _executionIds = row.executionId || ids.value;
  proxy.$modal.confirm('是否确认删除生产执行编号为"' + _executionIds + '"的数据项？').then(function() {
    return delExecution(_executionIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('productexecution/execution/export', {
    ...queryParams.value
  }, `execution_${new Date().getTime()}.xlsx`)
}

const scheduleOptions = ref([]);

// 获取排产列表
function getScheduleList() {
  listSchedule().then(response => {
    const existingScheduleIds = executionList.value.map(item => item.scheduleId);
    scheduleOptions.value = (response.rows || []).filter(
      item => !existingScheduleIds.includes(item.scheduleId)
    );
  });
}

// 排产ID变更处理
function handleScheduleChange(val) {
  if (val) {
    const selectedSchedule = scheduleOptions.value.find(item => item.scheduleId === val);
    if (selectedSchedule) {
      // 自动填充相关字段
      form.value.orderId = selectedSchedule.orderId;
      form.value.orderCode = selectedSchedule.orderCode;
      form.value.productId = selectedSchedule.productId;
      form.value.productCode = selectedSchedule.productCode;
      form.value.productName = selectedSchedule.productName;
      form.value.workshopId = selectedSchedule.workshopId;
      form.value.workshopName = selectedSchedule.workshopName;
      form.value.lineId = selectedSchedule.lineId;
      form.value.lineName = selectedSchedule.lineName;
      form.value.planQuantity = selectedSchedule.scheduleQuantity;
      form.value.unit = selectedSchedule.unit;
    }
  }
}

// 在打开对话框时获取排产列表
watch(open, (newVal) => {
  if (newVal) {
    getScheduleList();
  }
});

getList();
getScheduleList(); // 初始化获取排产列表

function handleStatusChange(row, val) {
  updateExecution({ executionId: row.executionId, executionStatus: val }).then(() => {
    proxy.$modal.msgSuccess("状态已更新");
    getList();
    if (val === "1") {
      // 获取当前日期，格式为YYYY-MM-DD
      const today = new Date();
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, '0');
      const day = String(today.getDate()).padStart(2, '0');
      const currentDate = `${year}-${month}-${day}`;
      
      updateExecution({ executionId: row.executionId, startTime: currentDate }).then(() => {
        proxy.$modal.msgSuccess("开始时间已更新");
        getList();
      });
    }
  });
}
</script>
