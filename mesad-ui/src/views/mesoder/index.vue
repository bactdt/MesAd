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
      <el-form-item label="订单数量" prop="orderQuantity">
        <el-input
          v-model="queryParams.orderQuantity"
          placeholder="请输入订单数量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-input
          v-model="queryParams.priority"
          placeholder="请输入优先级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="orderStatus" style="width: 230px;">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable>
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
      <el-table-column label="订单ID" align="center" prop="orderId" />
      <el-table-column label="订单编号" align="center" prop="orderCode" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="产品编码" align="center" prop="productCode" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="订单数量" align="center" prop="orderQuantity" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="计划开始" align="center" prop="planStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.planStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划结束" align="center" prop="planEndTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.planEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际开始" align="center" prop="actualStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.actualStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际结束" align="center" prop="actualEndTime" width="180">
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
    <el-dialog :title="title" v-model="open" width="1000px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <!-- 左侧表单 - 主要是可编辑字段 -->
          <el-col :span="12">
            <el-form-item label="订单编号" prop="orderCode">
              <el-input v-model="form.orderCode" placeholder="请输入订单编号" />
            </el-form-item>
            <el-form-item label="订单数量" prop="orderQuantity">
              <el-input v-model="form.orderQuantity" placeholder="请输入订单数量"/>
            </el-form-item>
            <el-form-item label="计划开始" prop="planStartTime">
              <el-date-picker clearable
                v-model="form.planStartTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择计划开始">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="计划结束" prop="planEndTime">
              <el-date-picker clearable
                v-model="form.planEndTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择计划结束">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="实际开始" prop="actualStartTime">
              <el-date-picker clearable
                v-model="form.actualStartTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择实际开始">
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
          </el-col>
          
          <!-- 右侧表单 - 主要是只读字段和选择按钮 -->
          <el-col :span="12">
            <el-form-item label="客户ID" prop="customerId">
              <div style="display: flex; align-items: center;">
                <el-input v-model="form.customerId" placeholder="请输入客户ID" disabled style="margin-right: 10px;"/>
                <el-button type="primary" icon="Search" @click="custViewOpen = true">选择</el-button>
              </div>
            </el-form-item>
            <el-form-item label="客户名称" prop="customerName">
              <el-input v-model="form.customerName" placeholder="请输入客户名称" disabled/>
            </el-form-item>
            <el-form-item label="产品ID" prop="productId">
              <div style="display: flex; align-items: center;">
                <el-input v-model="form.productId" placeholder="请输入产品ID" disabled style="margin-right: 10px;"/>
                <el-button type="primary" icon="Search" @click="prodViewOpen = true">选择</el-button>
              </div>
            </el-form-item>
            <el-form-item label="产品编码" prop="productCode">
              <el-input v-model="form.productCode" placeholder="请输入产品编码" disabled/>
            </el-form-item>
            <el-form-item label="产品名称" prop="productName">
              <el-input v-model="form.productName" placeholder="请输入产品名称" disabled/>
            </el-form-item>
            <el-form-item label="单位" prop="unit">
              <el-input v-model="form.unit" placeholder="请输入单位" disabled/>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-divider content-position="center">订单明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddMesOrderDetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteMesOrderDetail">删除</el-button>
          </el-col>
        </el-row>
        
        <!-- 订单明细表格 -->
        <el-table :data="mesOrderDetailList" :row-class-name="rowMesOrderDetailIndex" @selection-change="handleMesOrderDetailSelectionChange" ref="mesOrderDetail">
          <!-- 表格内容保持不变 -->
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="产品ID" prop="productId" width="150">
            <template #default="scope">
              <div style="display: flex; align-items: center;">
                <el-input v-model="scope.row.productId" placeholder="请输入产品ID" style="margin-right: 5px;" />
                <el-button type="primary" icon="edit" size="small" @click="handleSelectDetailProduct(scope.$index)"></el-button>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="产品编码" prop="productCode" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.productCode" placeholder="请输入产品编码" />
            </template>
          </el-table-column>
          <el-table-column label="产品名称" prop="productName" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.productName" placeholder="请输入产品名称" />
            </template>
          </el-table-column>
          <el-table-column label="规格型号" prop="specification" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.specification" placeholder="请输入规格型号" />
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="quantity" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.quantity" placeholder="请输入数量" />
            </template>
          </el-table-column>
          <el-table-column label="单位" prop="unit" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.unit" placeholder="请输入单位" />
            </template>
          </el-table-column>
          <el-table-column label="已排产数量" prop="planQuantity" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.planQuantity" placeholder="请输入已排产数量" />
            </template>
          </el-table-column>
          <el-table-column label="已生产数量" prop="producedQuantity" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.producedQuantity" placeholder="请输入已生产数量" />
            </template>
          </el-table-column>
          <el-table-column label="合格数量" prop="qualifiedQuantity" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.qualifiedQuantity" placeholder="请输入合格数量" />
            </template>
          </el-table-column>
          <el-table-column label="不合格数量" prop="unqualifiedQuantity" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.unqualifiedQuantity" placeholder="请输入不合格数量" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="status" width="150">
            <template #default="scope">
              <el-select v-model="scope.row.status" placeholder="请选择状态">
                <el-option
                  v-for="dict in sys_job_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 客户选择对话框 -->
    <el-dialog title="选择客户" v-model="custViewOpen" width="800px" append-to-body>
      <customerView @select="handleCustomerSelect"></customerView>
    </el-dialog>
    <!-- 产品选择对话框 -->
    <el-dialog title="选择产品" v-model="prodViewOpen" width="800px" append-to-body>
      <productView @productview="handleProductSelect"></productView>
    </el-dialog>
  </div>
</template>

<script setup name="Order">
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/mesoder/order";
import customerView from "./customerView.vue";
import productView from "./productView.vue";
const { proxy } = getCurrentInstance();
const { order_status, sys_job_status } = proxy.useDict('order_status', 'sys_job_status');

const orderList = ref([]);
const mesOrderDetailList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedMesOrderDetail = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const custViewOpen = ref(false);
const prodViewOpen = ref(false);
const currentDetailIndex = ref(-1); // 当前选择的明细索引

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orderCode: null,
    customerName: null,
    productName: null,
    orderQuantity: null,
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
    priority: [
      { required: true, message: "优先级不能为空", trigger: "blur" },
      { type: "number", transform: value => Number(value), min: 1, max: 10, message: "优先级必须是1-10之间的数字", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

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
  mesOrderDetailList.value = [];
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
    mesOrderDetailList.value = response.data.mesOrderDetailList;
    open.value = true;
    title.value = "修改订单管理";
  });
}
/** 客户选择 */
function handleCustomerSelect(customer) {
  if (customer && customer.customerId) {
    form.value.customerId = customer.customerId;
    form.value.customerName = customer.customerName;
    custViewOpen.value = false;
  } else {
    proxy.$modal.msgError("选择的客户数据无效");
  }
}
/** 提交按钮 */
function submitForm() {
  try{
    proxy.$refs["orderRef"].validate(valid => {
      if (valid) {
        form.value.mesOrderDetailList = mesOrderDetailList.value;
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
  catch(error){
    proxy.$modal.msgError("可能缺少某些字符");
    console.log(error);
  }
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

/** 订单明细序号 */
function rowMesOrderDetailIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 订单明细添加按钮操作 */
function handleAddMesOrderDetail() {
  let obj = {};
  obj.productId = "";
  obj.productCode = "";
  obj.productName = "";
  obj.specification = "";
  obj.quantity = "";
  obj.unit = "";
  obj.planQuantity = "";
  obj.producedQuantity = "";
  obj.qualifiedQuantity = "";
  obj.unqualifiedQuantity = "";
  obj.status = "";
  obj.remark = "";
  mesOrderDetailList.value.push(obj);
}

/** 订单明细删除按钮操作 */
function handleDeleteMesOrderDetail() {
  if (checkedMesOrderDetail.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的订单明细数据");
  } else {
    const mesOrderDetails = mesOrderDetailList.value;
    const checkedMesOrderDetails = checkedMesOrderDetail.value;
    mesOrderDetailList.value = mesOrderDetails.filter(function(item) {
      return checkedMesOrderDetails.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleMesOrderDetailSelectionChange(selection) {
  checkedMesOrderDetail.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('mesoder/order/export', {
    ...queryParams.value
  }, `order_${new Date().getTime()}.xlsx`)
}

getList();
/** 处理明细行产品选择按钮点击 */
function handleSelectDetailProduct(index) {
  currentDetailIndex.value = index;
  prodViewOpen.value = true;
}

/** 处理产品选择 */
function handleProductSelect(product) {
  try {
    if (product && typeof product === 'object' && product.productId) {
      // 如果是从明细行选择的产品
      if (currentDetailIndex.value >= 0) {
        // 更新明细行的产品信息
        const detailItem = mesOrderDetailList.value[currentDetailIndex.value];
        detailItem.productId = product.productId;
        detailItem.productCode = product.productCode;
        detailItem.productName = product.productName;
        detailItem.specification = product.specification || "";
        detailItem.unit = product.unit || "";
        detailItem.planQuantity = 0,
        detailItem.producedQuantity = 0,
        detailItem.qualifiedQuantity =  0,
        detailItem.unqualifiedQuantity = 0,
        detailItem.status = product.status || 0,
        detailItem.remark = ""
        
        // 重置索引
        currentDetailIndex.value = -1;
      } else {
        // 设置主表单的产品信息
        form.value.productId = product.productId;
        form.value.productCode = product.productCode;
        form.value.productName = product.productName;
        form.value.unit = product.unit;
        
        // 如果是修改订单，需要清空之前的订单明细并添加新的明细
        if (form.value.orderId) {
          // 清空现有明细
          mesOrderDetailList.value = [];
        }
        
        // 添加一条新的明细记录
        let detailItem = {
          productId: product.productId,
          productCode: product.productCode,
          productName: product.productName,
          specification: product.specification || "",
          quantity: form.value.orderQuantity || 0,
          unit: product.unit || "",
          planQuantity: 0,
          producedQuantity: 0,
          qualifiedQuantity: 0,
          unqualifiedQuantity: 0,
          status: product.status || 0,
          remark: ""
        };
        mesOrderDetailList.value.push(detailItem);
      }
      
      // 关闭产品选择对话框
      prodViewOpen.value = false;
      
      // 提示用户产品已更新
      proxy.$modal.msgSuccess("产品已更新");
    } else {
      proxy.$modal.msgError("选择的产品数据无效");
    }
  } catch (error) {
    console.error("产品选择处理错误:", error);
    proxy.$modal.msgError("处理产品选择时发生错误");
  }
}

</script>

