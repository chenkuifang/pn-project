# pn-project
这个一个基于springboot + spring mvc+  mybatis + thymeleaf +layui 的一个企业应用小型框架，后面我会集成更多现在既流行又实用的技术，包括shiro或 spring security、dubbo、利用REST风格的前后端分离、spring cloud等。
为了提供套路简单，逻辑清晰，我没少花时间，就是为了能让更多人能看懂的代码。望各位同学能多多支持，在学习或使用该框架的时候如果发现那里做的不够好的，希望不吝赐教，最后别忘了start一下项目哦，当然Fork一下最好，也期待你的代码贡献，哈哈.....

1.框架说明：该框架没有使用前后端分离的设计模式，因为现在应该还有蛮多公司都还在使用spring boot 或者 spring mvc + 后端模板（thymeleaf、freemark、jsp等）的形式做开发。
	这样做的确实简单，没有跨域，调试和部署起来也方便，在没有太大的要求的情况下，这样做也不错。但是这样一个Java工程师就得MVC统统都得涉及，
	说白了就是java后端工程师也得做前端的设计，数据展示，写一大堆html、js 等，着实痛苦，前后端分离的优点和缺点这里就不做陈述了。
	java工程师不能更好的专注于java的业务逻辑、设计、程序优化、io效率提高上确实是非常可惜的。
	所以后面我也会抽时间做一些前后端分离的学习型框架，分享我个人喜好的不一样的玩法。

2.说明一下该项目为了达到代码规范和简洁都做了些什么
	2.1 为了强制控制器方法的返回值只能为String、JsonResult,利用AOP做了强制判断，同时也利用AOP实现零侵入操作日志记录。
	2.2 封装了上下文工具类、JsonResultUitls等方便获取常用属性的工具类。
	2.3 为了能让各位同学都能读懂代码，我非常仔细的撸了很多详细的注释。
3.前端框架说明
	3.1 前端框架layui原始代码可在www.layui.com上面获得，如果出现左边的菜单点击无效，可多刷新，试多几下。layui框架升级好快，会有版本不兼容的情况
		目前还不想解决这个bug,前端的东西迟一点再去看吧，如果哪位同学在发现了这个问题，希望通过Fork贡献出来哦，不胜感激。
	3.2 如果不喜欢layui的，或者对该前端框架不熟的，可按自己要求切换即可，应该影响不大的，layui这个框架我也是最近才搞的，特别不适应它的js按需加载，在这个前端
	框架上也花了一些时间，后面极有可能会放弃而转bootstrap。
