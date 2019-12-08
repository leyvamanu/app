
'use strict';


var twobit = function( num ) {
	return num >= 10 ? num + '' : '0' + num;
};

var regTime = /^[0-9]{1,2}:[0-9]{1,2}$/;
var timepicker = {};

var nullFun = function () {};


var hourStr = new Array( 24 ).fill( null ).map(function(t,i){
	var val = twobit( i ); 
	return '<li class="cell-2 js-hour-cell" data-val="' + val + '">' + val + '</li>';
}).join('');

var minuteStr = new Array( 12 ).fill( null ).map(function(t,i){
	var val = twobit( i *5 ); 
	return  '<li class="cell-2 js-minute-cell" data-val="' + val + '">' + val + '</li>';
}).join('');

var content = $('<div class="timepicker">\
		<div v-show class="title">Escoge una hora</div>\
			<div class="chose-all">\
				<div class="handle">\
					<div class="cell-4"><a class="icon-up js-plus-houer"></a></div>\
					<div class="cell-2"></div>\
					<div class="cell-4"><a class="icon-up js-plus-minute"></a></div>\
				</div>\
				<div class="text">\
					<div class="cell-4"><a class="js-hour-show" title="hora"></a></div>\
					<div class="cell-2">:</div>\
					<div class="cell-4"><a class="js-minute-show" title="minuto"></a></div>\
				</div>\
				<div class="handle">\
					<div class="cell-4"><a class="icon-down js-minus-houer"></a></div>\
					<div class="cell-2"></div>\
					<div class="cell-4"><a class="icon-down js-minus-minute"></a></div>\
				</div>\
			</div>\
			<div class="chose-hour">\
				<ul class="handle">' + hourStr + '</ul>\
			</div>\
			<div class="chose-minute">\
				<ul class="handle">' + minuteStr + '</ul>\
			</div>\
		</div>\
	</div>');
content.find('a').attr('href','javascript:void(0);');
timepicker.content = content;
timepicker.title = content.find('.title');
timepicker.choseAll = content.find('.chose-all');
timepicker.choseMinute = content.find('.chose-minute');
timepicker.choseHour = content.find('.chose-hour');
timepicker.hourShow = content.find('.js-hour-show');
timepicker.minuteShow = content.find('.js-minute-show');


timepicker.update = function () {
	this.inputTarget.val( twobit( this.hour ) + ':' + twobit( this.minute ) );
	this.minuteShow.text( twobit( this.minute ) );
	this.hourShow.text( twobit( this.hour ) );
	this.inputTarget.$timepickerUpdate();
	return this;
};


timepicker.bindEvent = function () {
	var thisTimePicker = this;
	if( thisTimePicker.hasBind ) return;
	thisTimePicker.hasBind = true;
	
	this.content.on('click','.js-minus-minute',function() {
		var minute = thisTimePicker.minute;
		if( minute <= 0 ){
			thisTimePicker.minute = 59;
		} else {
			thisTimePicker.minute--;
		}
		thisTimePicker.update();
	
	
	}).on('click','.js-plus-minute',function() {
		var minute = thisTimePicker.minute;
		if( minute >= 59 ){
			thisTimePicker.minute = 0;
		} else {
			thisTimePicker.minute++;
		}

		thisTimePicker.update();
	
	}).on('click','.js-plus-houer',function() {
		var hour = thisTimePicker.hour;
		if( hour >= 23 ){
			thisTimePicker.hour = 0;
		} else {
			thisTimePicker.hour++;
		}
		thisTimePicker.update();
	
	
	}).on('click','.js-minus-houer',function() {
		var hour = thisTimePicker.hour;
		if( hour <= 0 ){
			thisTimePicker.hour = 23;
		} else {
			thisTimePicker.hour--;
		}
		thisTimePicker.update();
	
	
	}).on('click','.js-minute-cell',function () {
		thisTimePicker.minute = +this.getAttribute('data-val');
		thisTimePicker.update();
		thisTimePicker.choseMinute.hide();
		thisTimePicker.choseAll.show();
		thisTimePicker.title.text('por favor elija');
	
	
	}).on('click','.js-hour-cell',function () {
		thisTimePicker.hour = +this.getAttribute('data-val');
		thisTimePicker.update();
		thisTimePicker.choseHour.hide();
		thisTimePicker.choseAll.show();
		thisTimePicker.title.text('');
	
	}).on('click',function(e) {
		e.stopPropagation();
	});

	
	thisTimePicker.hourShow.on('click',function() {
		thisTimePicker.choseAll.hide();
		thisTimePicker.choseHour.show();
		thisTimePicker.title.text('seleccione hora');
	});

	
	thisTimePicker.minuteShow.on('click',function() {
		thisTimePicker.choseAll.hide();
		thisTimePicker.choseMinute.show();
		thisTimePicker.title.text('seleccione minutos');
	});
};


$.timepicker = timepicker;


$.fn.timepicker = function( option ) {
	var t = this;
	var hour;
	var minute;
	var timepickerObj = $.timepicker;
	var $body = $('html');
	
	if( !this[0].nodeName || this[0].nodeName !== 'INPUT' ){
		return;
	}
	
	this.$timepickerUpdate = nullFun;
	
	
	this.off('click').on('click',function(e) {
		var val = this.value;
		if( regTime.test( val ) ){
			val = val.split(':');
			hour = +val[0];
			minute = +val[1];
		} else {
			val = new Date();
			hour = val.getHours();
			minute = val.getMinutes();
		}
		var left = this.offsetLeft + 'px';
		var top = ( this.offsetTop + this.offsetHeight ) + 'px';
		
		timepickerObj.inputTarget = t;
		timepickerObj.content.appendTo( this.offsetParent ).css( { left : left, top : top } );
		timepickerObj.hour = hour;
		timepickerObj.minute = minute;
		timepickerObj.choseAll.show();
		timepickerObj.choseHour.hide();
		timepickerObj.choseMinute.hide();
		timepickerObj.update();
		$.timepicker.bindEvent();
		e.stopPropagation();
		$body.one('click',function() {
			timepickerObj.content.off().remove();
			timepickerObj.hasBind = false;
		});
	});
	this.off('keydown').on('keydown',function() {
		return false;
	});
	this.update = function( fun ) {
		if( $.isFunction( fun ) ) this.$timepickerUpdate = fun;
		else this.$timepickerUpdate = nullFun;
	};
	return this;
};

