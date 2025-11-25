function callData(url, param, succ,err) {
    $.ajax({
        async: false,
        url: url,
        type: 'post',
        dataType: 'json',
        data: param,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(err){
                err(XMLHttpRequest, textStatus, errorThrown);
            }
        },
        success: function (data) {
            if(succ) {
                succ(data);
            }
        }
    });
}
function callDataJson(url, param, succ,err) {
    $.ajax({
        async: false,
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(param),
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(err){
                err(XMLHttpRequest, textStatus, errorThrown);
            }
        },
        success: function (data) {
            if(succ) {
                succ(data);
            }
        }
    });
}
function showDeil(event) {
    var $detil = $(event).parent('span').parent('li').next('.info');
    if($detil.is(':hidden')){
        $detil.show();
        $(event).text('收起')
    }else {
        $detil.hide();
        $(event).text('展开')
    }
}
