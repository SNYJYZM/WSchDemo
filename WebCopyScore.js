// Your code here...
var textarea1 = document.createElement('textarea');
textarea1.rows = '10';
textarea1.cols = '20';
textarea1.id = 'textarea';
textarea1.textContent = "平时成绩";
document.body.appendChild(textarea1);

textarea1.addEventListener('input',function () {
    console.log('demo....')
    console.log('Text changed to:', this.value);
    const pastedData = this.value;
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

    var sum = 1
    arr.forEach(item => {
        // console.log(sum)
        console.log(item)
        console.log(item[0])
        console.log(item[1])
        // CHKPSCJ1

        var inputElement1 = document.getElementById('CHKPSCJ' + sum);
        inputElement1.value = item[0];

        var inputElement2 = document.getElementById('CHKQMCJ' + sum);
        inputElement2.value = item[1];
        sum++
    });

    var summ=1
    arr.forEach(item => {
        var inputElement1 = document.getElementById('CHKPSCJ' + summ);
        inputElement1.focus();
        summ++
    });


})


