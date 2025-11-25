$(function() {
    var mySwiper = new Swiper('.swiper-container',{
        initialSlide: $('#index').val()?$('#index').val():0, // 坐标索引值初始是第几个
        on:{
            init: function(){
                console.info("init")
                this.emit('slideChangeTransitionEnd');//在初始化时触发一次slideChangeTransitionEnd事件
            },
            slideChangeTransitionEnd: function(){
                $('.navbar').find("a").removeClass("acctive");
                var index = this.activeIndex;
                var $a = $('.navbar').find("a[data-value="+index+"]");
                $a.addClass("acctive");
                navbarAcctive(this.activeIndex);
            }
        }
    });
    $('.navbar').find("a").on('click', function () {
        $('.navbar').find("a").removeClass("acctive");
        $(this).addClass("acctive");
        var index = $(this).data("value");
        mySwiper.slideTo(index, 1000, true);
        navbarAcctive(index);
    });

    $('a.title-left').on('click',function () {
        mySwiper.slidePrev();
    });
    $('a.title-right').on('click',function () {
        mySwiper.slideNext();
    });
});
function navbarAcctive(index) {
    switch(index){
        case 0:
            loadOrder();
            break;
        case 1:
            loadRecord();
            break;
        default:
    }
}
var order_record;
var user_list = [];
function loadOrder() {
    var $layer = $('#order');
    if(!order_record) {
        order_record = $layer.dropload({
            loadUpFn: function (me) {
                getDataList();
                me.resetload();//重置
            }
        });
        getDataList();
    }
    function getDataList() {
        callData("/admin/selectUserList", {}, function(res) {
            if (res.code == 0) {
                user_list = res.data;
                var listHtml = template("orderList", {list:user_list});
                $layer.find('.data-list').html(listHtml);
            }else {
                layer.alert(res.message)
            }
        });
    }
}
var dropload_record;
function loadRecord() {
    var $layer = $('#record');
    if(!dropload_record) {
        dropload_record = $layer.dropload({
            loadUpFn: function (me) {
                getDataList();
                me.resetload();//重置
            }
        });
        getDataList();
    }
    function getDataList() {
        callData("/admin/selectReport", {}, function(res) {
            if (res.code == 0) {
                var resJson = res.data;
                var listHtml = template("detil", {list:resJson});
                $layer.find('.data-list').html(listHtml);
                // 1. 展开/收起详情
                $layer.on('click', '.expand-btn', function(e) {
                    e.preventDefault();
                    const $btn = $(this);
                    const $info = $btn.closest('li').next('.info');

                    // 切换显示状态
                    if ($info.is(':hidden')) {
                        $info.slideDown(300); // 平滑展开
                        $btn.text('收起');
                    } else {
                        $info.slideUp(300); // 平滑收起
                        $btn.text('展开');
                    }

                    // 关闭其他展开的详情（可选，避免多个详情同时展开）
                    $('.info').not($info).slideUp(300).prev('li').find('.expand-btn').text('展开');
                });

// 2. 排序切换（特码/生肖）
                $layer.on('click', '.sort-btn', function(e) {
                    e.preventDefault();
                    const $btn = $(this);
                    const $info = $btn.closest('.info');
                    const sortType = $btn.data('type');

                    // 切换按钮激活状态
                    $btn.siblings('.sort-btn').removeClass('active');
                    $btn.addClass('active');

                    // 切换表格行显示
                    if (sortType === 'code') {
                        $info.find('.code-row').show();
                        $info.find('.shengxiao-row').hide();
                    } else if (sortType === 'shengxiao') {
                        $info.find('.code-row').hide();
                        $info.find('.shengxiao-row').show();
                    }
                });
                // 3. 表格行hover效果
                $layer.on('mouseenter', '.code-row, .shengxiao-row', function() {
                    $(this).css('background-color', '#f9fafb');
                });
                $layer.
                on('mouseleave', '.code-row, .shengxiao-row', function() {
                    $(this).css('background-color', 'transparent');
                });
            }else {
                layer.alert(res.message)
            }
        });
    }
}
function saveBill(index,e) {
    var param = user_list[index];
    param.test = $(e).val()
    callData("/admin/saveBill", param, function (res) {
        if (res.code == 0) {
            layer.msg("保存成功");
        } else {
            layer.alert(res.message)
        }
    });
}
function saveLottery() {
    layer.prompt({
        formType: 0,
        title: '开码号码',
        maxlength: 2
    }, function (value, index, elem) {
        if(value&&value<50&&value>0) {
            callData("/admin/saveLottery", {code: value}, function (res) {
                if (res.code == 0) {
                    layer.close(index);
                    layer.msg("计算成功");
                } else {
                    layer.alert(res.message)
                }
            });
        }
    })
}
function saveUser() {
    layer.prompt({
        formType: 0,
        title: '姓名',
        maxlength: 2
    }, function (value, index, elem) {
        if(value) {
            callData("/admin/saveUser", {name: value}, function (res) {
                if (res.code == 0) {
                    layer.close(index);
                    layer.msg("添加成功");
                } else {
                    layer.alert(res.message)
                }
            });
        }
    })
}