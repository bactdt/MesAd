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
      <el-form-item label="客户名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户名称"
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
      <el-form-item label="计划开始" prop="planStartTime">
        <el-date-picker clearable
          v-model="queryParams.planStartTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择计划开始">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="计划结束" prop="planEndTime">
        <el-date-picker clearable
          v-model="queryParams.planEndTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择计划结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际开始" prop="actualStartTime">
        <el-date-picker clearable
          v-model="queryParams.actualStartTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择实际开始">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际结束" prop="actualEndTime">
        <el-date-picker clearable
          v-model="queryParams.actualEndTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择实际结束">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-input
          v-model="queryParams.priority"
          placeholder="请输入优先级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="orderStatus" >
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable style="width: 200px;">
          <el-option
            v-for="dict in order_status"
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
          v-hasPermi="['mesoder:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mesoder:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mesoder:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['mesoder:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="订单ID" align="center" prop="orderId" /> -->
      <el-table-column label="订单编号" align="center" prop="orderCode" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="订单数量" align="center" prop="orderQuantity" />
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
      <el-table-column label="实际开始时间" align="center" prop="actualStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.actualStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际结束时间" align="center" prop="actualEndTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.actualEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优先级" align="center" prop="priority" />
      <el-table-column label="订单状态" align="center" prop="orderStatus">
        <template #default="scope">
          <dict-tag :options="order_status" :value="scope.row.orderStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['mesoder:order:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['mesoder:order:remove']">删除</el-button>
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

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="orderCode">
          <el-input v-model="form.orderCode" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="客户ID" prop="customerId">
          <div style="display: flex; align-items: center;">
            <el-input v-model="form.customerId" placeholder="请输入客户ID" disabled style="margin-right: 10px;"/>
            <el-button type="primary" size="small"  @click="viewopen=true">选择客户</el-button>
          </div>
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户名称" disabled/>
        </el-form-item>
        <el-form-item label="产品ID" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入产品ID" />
        </el-form-item>
        <el-form-item label="产品编码" prop="productCode">
          <el-input v-model="form.productCode" placeholder="请输入产品编码" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="订单数量" prop="orderQuantity">
          <el-input v-model="form.orderQuantity" placeholder="请输入订单数量" />
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
        <el-form-item label="实际结束" prop="actualEndTime">
          <el-date-picker clearable
            v-model="form.actualEndTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择实际结束">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优先级" prop="priority">
          <el-input v-model="form.priority" placeholder="请输入优先级" />
        </el-form-item>
        <el-form-item label="订单状态" prop="orderStatus">
          <el-select v-model="form.orderStatus" placeholder="请选择订单状态">
            <el-option
              v-for="dict in order_status"
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
    <el-dialog v-model="viewopen" title="选择客户">
      <customerView @select="handleCustomerSelect">

      </customerView>
    </el-dialog>
  </div>
</template>

<script setup name="Order">
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/mesoder/order";
import customerView from "@/views/mesoder/order/customerView.vue";
const { proxy } = getCurrentInstance();
const { order_status } = proxy.useDict('order_status');

const orderList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const viewopen = ref(false);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orderCode: null,
    customerName: null,
    productName: null,
    orderQuantity: null,
    unit: null,
    planStartTime: null,
    planEndTime: null,
    actualStartTime: null,
    actualEndTime: null,
    priority: null,
    orderStatus: null,
  },
  rules: {
    customerId: [
      { required: true, message: "客户ID不能为空", trigger: "blur" }
    ],
    customerName: [
      { required: true, message: "客户名称不能为空", trigger: "blur" }
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
    orderQuantity: [
      { required: true, message: "订单数量不能为空", trigger: "blur" }
    ],
    unit: [
      { required: true, message: "单位不能为空", trigger: "blur" }
    ],
    priority: [
      { required: true, message: "优先级不能为空", trigger: "blur" },
      { type: "number", transform: value => Number(value), min: 1, max: 10, message: "优先级必须是1-10之间的数字", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

function handleCustomerSelect(customer) {
  form.value.customerId = customer.customerId;
  form.value.customerName = customer.customerName;
  viewopen.value = false;
}

/** 查询订单管理列表 */
function getList() {
  loading.value = true;
  listOrder(queryParams.value).then(response => {
    orderList.value = response.rows;
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
    orderId: null,
    orderCode: null,
    customerId: null,
    customerName: null,
    productId: null,
    productCode: null,
    productName: null,
    orderQuantity: null,
    unit: null,
    planStartTime: null,
    planEndTime: null,
    actualStartTime: null,
    actualEndTime: null,
    priority: null,
    orderStatus: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("orderRef");
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
  ids.value = selection.map(item => item.orderId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加订单管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _orderId = row.orderId || ids.value
  getOrder(_orderId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改订单管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["orderRef"].validate(valid => {
    if (valid) {
      if (form.value.orderId != null) {
        updateOrder(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addOrder(form.value).then(response => {
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
  const _orderIds = row.orderId || ids.value;
  proxy.$modal.confirm('是否确认删除订单管理编号为"' + _orderIds + '"的数据项？').then(function() {
    return delOrder(_orderIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('mesoder/order/export', {
    ...queryParams.value
  }, `order_${new Date().getTime()}.xlsx`)
}

getList();
</script>
