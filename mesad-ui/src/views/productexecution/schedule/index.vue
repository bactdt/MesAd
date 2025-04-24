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
    
      <el-form-item label="排产状态" prop="scheduleStatus">
        <el-select v-model="queryParams.scheduleStatus" placeholder="请选择排产状态" clearable style="width: 170px;">
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
          v-hasPermi="['productexecution:schedule:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['productexecution:schedule:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['productexecution:schedule:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['productexecution:schedule:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scheduleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="排产ID" align="center" prop="scheduleId" />
      <el-table-column label="订单编号" align="center" prop="orderCode" />
      <el-table-column label="产品编码" align="center" prop="productCode" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="车间名称" align="center" prop="workshopName" />
      <el-table-column label="生产线名" align="center" prop="lineName" />
      <el-table-column label="排产数量" align="center" prop="scheduleQuantity" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="计划开始时间" align="center" prop="planStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.planStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划结束时间" align="center" prop="planEndTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.planEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="排产状态" align="center" prop="scheduleStatus">
        <template #default="scope">
          <el-radio-group
            v-model="scope.row.scheduleStatus"
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
      <!-- 删除操作列 -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单排产对话框 -->
    <el-dialog :title="title" v-model="open" width="900px" append-to-body>
      <el-form ref="scheduleRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <!-- 左侧表单 -->
          <el-col :span="12">
            <el-form-item label="订单编号" prop="orderCode">
              <div style="display: flex; align-items: center;">
                <el-input v-model="form.orderCode" placeholder="请选择订单编号" disabled style="margin-right: 10px;"/>
                <el-button
                  type="primary"
                  icon="Search"
                  @click="handleSelectOrder">选择订单</el-button>
              </div>
            </el-form-item>
            <el-form-item label="产品编码" prop="productCode">
              <el-input v-model="form.productCode" placeholder="请输入产品编码" disabled/>
            </el-form-item>
            <el-form-item label="产品名称" prop="productName">
              <el-input v-model="form.productName" placeholder="请输入产品名称" disabled/>
            </el-form-item>
            <el-form-item label="车间ID" prop="workshopId">
              <el-input v-model="form.workshopId" placeholder="请输入车间ID" />
            </el-form-item>
            <el-form-item label="车间名称" prop="workshopName">
              <el-input v-model="form.workshopName" placeholder="请输入车间名称" />
            </el-form-item>
            <el-form-item label="生产线ID" prop="lineId">
              <el-input v-model="form.lineId" placeholder="请输入生产线ID" />
            </el-form-item>
            <el-form-item label="生产线名称" prop="lineName">
              <el-input v-model="form.lineName" placeholder="请输入生产线名称" />
            </el-form-item>
          </el-col>
          
          <!-- 右侧表单 -->
          <el-col :span="12">
            <el-form-item label="排产数量" prop="scheduleQuantity">
              <el-input v-model="form.scheduleQuantity" placeholder="请输入排产数量" />
            </el-form-item>
            <el-form-item label="单位" prop="unit">
              <el-input v-model="form.unit" placeholder="请输入单位" />
            </el-form-item>
            <el-form-item label="计划开始时间" prop="planStartTime">
              <el-date-picker clearable
                v-model="form.planStartTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择计划开始时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="计划结束时间" prop="planEndTime">
              <el-date-picker clearable
                v-model="form.planEndTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择计划结束时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="实际开始时间" prop="actualStartTime">
              <el-date-picker clearable
                v-model="form.actualStartTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择实际开始时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="实际结束时间" prop="actualEndTime">
              <el-date-picker clearable
                v-model="form.actualEndTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择实际结束时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="排产状态" prop="scheduleStatus">
              <el-select v-model="form.scheduleStatus" placeholder="请选择排产状态">
                <el-option
                  v-for="dict in mes_production_execution_satus"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        
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
    
    <!-- 订单选择对话框 -->
    <el-dialog title="选择订单" v-model="orderSelectOpen" width="800px" append-to-body>
      <el-table
        v-loading="orderSelectLoading"
        :data="orderSelectList"
        @selection-change="handleOrderSelectionChange"
        ref="orderSelectTable"
        :row-key="row => row.orderId"
        tooltip-effect="dark"
        style="width: 100%"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="订单编号" align="center" prop="orderCode" />
        <el-table-column label="产品名称" align="center" prop="productName" />
        <el-table-column label="产品编码" align="center" prop="productCode" />
        <el-table-column label="产品单位" align="center" prop="unit" />
      </el-table>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="confirmSelectOrder">确 定</el-button>
          <el-button @click="cancelSelectOrder">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Schedule">
import { listSchedule, getSchedule, delSchedule, addSchedule, updateSchedule ,listSchedulechangeall} from "@/api/productexecution/schedule";
import { updateOrder } from "@/api/mesoder/order";
const { proxy } = getCurrentInstance();
const { mes_production_execution_satus } = proxy.useDict('mes_production_execution_satus');

const scheduleList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const orderSelectOpen = ref(false);
const orderSelectList = ref([]);
const orderSelectLoading = ref(false);
const selectedOrderRow = ref(null);

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
    scheduleStatus: null,
  },
  rules: {
    orderId: [
      { required: true, message: "订单ID不能为空", trigger: "blur" }
    ],
    orderCode: [
      { required: true, message: "订单编号不能为空", trigger: "blur" }
    ],
    detailId: [
      { required: true, message: "订单明细ID不能为空", trigger: "blur" }
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
    scheduleQuantity: [
      { required: true, message: "排产数量不能为空", trigger: "blur" }
    ],
    unit: [
      { required: true, message: "单位不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询订单排产列表 */
function getList() {
  loading.value = true;
  listSchedule(queryParams.value).then(response => {
    scheduleList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}
function handleSelectOrder() {
  orderSelectLoading.value = true;
  listSchedulechangeall().then(response => {
    orderSelectList.value = response.rows || [];
    orderSelectLoading.value = false;
  }).catch(() => {
    orderSelectLoading.value = false;
  });
  orderSelectOpen.value = true;
}

// 订单选择变更
function handleOrderSelectionChange(selection) {
  selectedOrderRow.value = selection.length === 1 ? selection[0] : null;
}

// 确认选择订单
function confirmSelectOrder() {
  if (selectedOrderRow.value) {
    form.value.orderId = selectedOrderRow.value.orderId;
    form.value.orderCode = selectedOrderRow.value.orderCode;
    form.value.detailId = selectedOrderRow.value.detailId;
    form.value.productId = selectedOrderRow.value.productId;
    form.value.productCode = selectedOrderRow.value.productCode;
    form.value.unit = selectedOrderRow.value.unit;
    // 如果API返回了产品名称等其他信息，也可以一并填入表单
    if (selectedOrderRow.value.productName) {
      form.value.productName = selectedOrderRow.value.productName;
    }
  }
  orderSelectOpen.value = false;
}

// 取消选择订单
function cancelSelectOrder() {
  selectedOrderRow.value = null;
  orderSelectOpen.value = false;
}
// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    scheduleId: null,
    orderId: null,
    orderCode: null,
    detailId: null,
    productId: null,
    productCode: null,
    productName: null,
    workshopId: null,
    workshopName: null,
    lineId: null,
    lineName: null,
    scheduleQuantity: null,
    unit: null,
    planStartTime: null,
    planEndTime: null,
    actualStartTime: null,
    actualEndTime: null,
    scheduleStatus: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("scheduleRef");
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
  ids.value = selection.map(item => item.scheduleId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加订单排产";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _scheduleId = row.scheduleId || ids.value
  getSchedule(_scheduleId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改订单排产";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["scheduleRef"].validate(valid => {
    if (valid) {
      if (form.value.scheduleId != null) {
        updateSchedule(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSchedule(form.value).then(response => {
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
  const _scheduleIds = row.scheduleId || ids.value;
  proxy.$modal.confirm('是否确认删除订单排产编号为"' + _scheduleIds + '"的数据项？').then(function() {
    return delSchedule(_scheduleIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('productexecution/schedule/export', {
    ...queryParams.value
  }, `schedule_${new Date().getTime()}.xlsx`)
}

getList();
function handleStatusChange(row, val) {
  // 这里 val 是新的状态值
  updateSchedule({ scheduleId: row.scheduleId, scheduleStatus: val }).then(() => {
    getList();
    if (val === "3") {
      // 如果状态为已完成，则更新实际结束时间为当前时间
      row.actualEndTime = new Date();
      if(row.orderId){
        updateOrder({ orderId: row.orderId, orderStatus: 1 }).then(() => {
          proxy.$modal.msgSuccess("订单状态已更新为已排产");
        });
      }
    }
  });
}
</script>
