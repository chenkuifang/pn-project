# pn-project
这个一个基于springboot + spring mvc+  mybatis + thymeleaf +layui 的一个企业应用小型框架。
<p>后面我会集成更多现在既流行又实用的技术，包括shiro或 spring security的签名授权、dubbo、利用REST风格的前后端分离、spring cloud等。</p>
<p>为了提供套路简单，逻辑清晰，我没少花时间，就是为了能让更多人能看懂，很多地方都是经过多次考虑而使用最简单的实现方式。</p>
<p>望各位同学能多多支持，在学习或使用该框架的时候如果发现那里做的不够好的，希望不吝赐教，最后别忘了start一下项目哦，如果有兴趣也可Fork该仓库，也期待你的代码贡献，哈哈.....</p>

<h2>第一次运行系统</h2>
<ul>
	<li>JDK1.8或以上，Mysql5.7或以上</li>	
	<li>运行pn_sql 数据库脚本</li>
	<li>默认用户是全世界同类都懂的admin,123456</li>
        </ul>
<h2>1.框架说明：</h2>
<ul>
        <li>该框架没有使用前后端分离的设计模式，因为现在应该还有蛮多公司都还在使用spring boot 或者 spring mvc + 后端模板（thymeleaf、freemark、jsp等）的形式做开发。</li>
	<li>这样做的确实简单，没有跨域，调试和部署起来也方便，在没有太大的要求的情况下，这样做也不错。但是这样一个Java工程师就得MVC统统都得涉及，
                说白了就是java后端工程师也得做前端的设计，数据展示，写一大堆html、js 等，着实痛苦，前后端分离的优点和缺点这里就不做陈述了，但我认为优点会比缺点多得多。</li>
        <li>java工程师不能更好的专注于java的业务逻辑、设计、程序优化、io效率提高上确实是非常可惜的。</li>
        <li>所以后面我也会抽时间做一些前后端分离的学习型框架，分享我个人喜好的不一样的玩法。</li>
</ul>
<h2>2.说明一下该项目为了达到代码规范和简洁都做了些什么</h2>
        <ul>
        <li>为了强制控制器方法的返回值只能为String、JsonResult,利用AOP做了强制判断，在这里你可能会问，为什么不是返回ModelAndView，我只能说为了简洁。同时也利用AOP实现零侵入操作日志记录。</li>
	<li> 封装了上下文工具类、JsonResultUitls等方便获取常用属性的工具类。</li>
	<li> 为了能让各位同学都能读懂代码，我非常仔细的撸了很多详细的注释。</li>
        </ul>
<h2>3.前端框架说明</h2>
        <ul>
	
	<li> 如果不喜欢layui的，或者对该前端框架不熟的，可按自己要求切换即可，应该影响不大的，layui这个框架我也是最近才搞的，特别不适应它的js按需加载，在这个前端
	框架上也花了一些时间，后面极有可能会放弃而转bootstrap。</li>
        </ul>
<h2>4.使用该系统时简单的建议</h2>
        <ul>
	<li>非常建议一个实际有链接的菜单对应一个控制器，每个链接的名称跟ViewName保持一致，这样更容易维护</li>
	<li>为了保持SQL的简洁性和易维护性，不建议在SQL中有任何逻辑判断，把该有的逻辑判断交给java吧</li>
	<li>不要随便在for循环中使用String </li>
        </ul>
