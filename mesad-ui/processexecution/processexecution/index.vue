async function submitForm() {
  try {
    await proxy.$refs["processexecutionRef"].validate();
    
    // 打印表单数据，帮助调试
    console.log("提交的表单数据:", JSON.stringify(form.value));
    
    if (form.value.id != null) {
      updateProcessexecution(form.value).then(response => {
        proxy.$modal.msgSuccess("修改成功");
        open.value = false;
        getList();
      }).catch(error => {
        // 更详细的错误日志
        console.error("更新失败:", error);
        console.error("错误详情:", error.response ? error.response.data : "无响应数据");
        proxy.$modal.msgError("更新失败: " + (error.message || "未知错误"));
      });
    } else {
      addProcessexecution(form.value).then(response => {
        proxy.$modal.msgSuccess("新增成功");
        open.value = false;
        getList();
      }).catch(error => {
        // 更详细的错误日志
        console.error("新增失败:", error);
        console.error("错误详情:", error.response ? error.response.data : "无响应数据");
        proxy.$modal.msgError("新增失败: " + (error.message || "未知错误"));
      });
    }
  } catch (error) {
    console.error("表单验证失败:", error);
  }
}