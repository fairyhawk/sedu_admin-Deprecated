function myConfirm(url) {
	if (window.confirm('确定删除吗？') == true) {
		window.location.href = url;
	}
}