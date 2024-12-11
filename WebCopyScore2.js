document.querySelectorAll('input').forEach(function (input) {
    input.addEventListener('paste', function (event) {
        event.preventDefault(); // 可选：阻止默认粘贴行为
        // 获取粘贴板数据
        const clipboardData = event.clipboardData || window.clipboardData;
        const pastedData = clipboardData.getData('Text');

        // 初始化用于存储解析后的数据的数组
        let arr = [];
        try {
            arr = pastedData.split('\n')
                .filter(item => item !== '') // 兼容Excel行末\n，防止出现多余空行
                .map(item => item.split('\t')) // 将每行按制表符分割成列
                .map(item => {
                    // 去掉每列中的\r字符，使用模板字符串
                    return item.map(str => str.replace(/\r/g, ''));
                });
        } catch (error) {
            console.error("Error parsing pasted data:", error);
            return; // 在遇到异常时终止处理
        }

        var preIdAll = event.target.id
        var preID = getLastNumber(event.target.id);
        var result = preIdAll.replace(new RegExp(preID, ''), "");
        let preNumber = Number(preID)

        arr.forEach(item => {
            var inputElement1 = document.getElementById(result+ preNumber);
            inputElement1.value='';
            inputElement1.value = item[0];
            preNumber++;
        });


    });
});


function getLastNumber(str) {
    const match = str.match(/(\d+)$/);
    return match ? match[1] : null;
}
