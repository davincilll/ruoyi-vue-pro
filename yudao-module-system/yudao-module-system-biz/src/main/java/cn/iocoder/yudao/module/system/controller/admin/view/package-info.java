package cn.iocoder.yudao.module.system.controller.admin.view;
// TODO: 对于每一个domain都会生成一个view模型，前端请求每一个domain的同时都会对请求对应的view
// view对象含有domain在当前操作人和当前租户人下显示的字段显示的button。
// 首先会有一个base-view然后根据权限进行减少显示。
// 还会建立每个字段对应的显示的名称。
// 还要建立对这个domain可操作的一些行为。将后端的一些约束通过这个传到前端
// 建立一套domain上的注解 需要预先进行扫描并缓存下来 参考django看下有哪些项
