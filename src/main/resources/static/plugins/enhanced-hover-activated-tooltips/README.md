# easyTooltip: 鼠标 hover 出现提示内容

## 一、通过 Node 引用

```javascript
npm i alvin-tooltip
```

在 VUE 的 SPA 中的使用示例：

```html
<template>
  <div id="main">
    <div id="tooltip"></div>
  </div>
</template>
<script>
import createToolTip from "alvin-tooltip";
export default {
  name: "Tooltip",
  data() {
    return {
      tooltip: "",
    };
  },
  mounted() {
    var cfg = {
      targetNodeId: "tooltip",
      tooltipDir: "right",
      content: "I am a sample.",
      tooltipPosition: "",
    };
    this.tooltip = createToolTip(cfg);
  },
};
</script>
<style lang="scss" scoped>
#tooltip { width: 150px; height: 50px; line-height: 48px; background: #ddd; text-align: center; }
</style>
```

## 二、通过 script 脚本引入
```html
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="easyTooltip.min.js"></script>
<div id="tooltip"></div>
```
引入上面两个文件之后，即可调用该插件了：
```javascript
$("#tooltip").easyTooltip({
	tooltipDir: "left",
	content: "I am a sample."
});
```

## 三、插件可供设置的参数及参数的默认值

```javascript
$(targetNode).easyTooltip({
   targetNodeId: "tooltip", /*鼠标 hover 的目标节点*/
	/************ tooltip 结构参数 ************/
	tooltipId: "easyTooltip",/*tooltip 最外层元素的 ID*/
	tooltipClass: "easyTooltip",/*tooltip 最外层元素的 Class*/
	content: "",/*设置 tooltip 的内容，可以包含 html 标签元素*/
	existedContentId: "",/*将已有元素的内容作为 tooltip 的内容，若不为空，则将替换 content 所设置的内容*/
	tooltipDir: "top",/*tooltip 出现的方向(top\right\bottom\left)*/
	xOffset: 5,/*tooltip 在 X 轴离鼠标的距离*/       
	yOffset: 5,/*tooltip 在 Y 轴离鼠标的距离*/
	clickRemove: false,/*是否点击隐藏 tooltip*/
	tooltipPosition: 'absolute',/*tooltip 是否会跟随鼠标移动(absolute\relative)*/
	/************ tooltip 样式参数 ************/
	defaultRadius: "3px",
	tooltipZindex: 10000,
	tooltipPadding: "10px 15px",
	tooltipBgColor: "rgba(200,200,200,0.7)",
	tooltipFtSize: "14px",
	tooltipLineHeight: "24px",
	tooltipFtColor: "#000",
	tooltipOpacity: 1,
	tooltipArwBorderWidth: 6
});
```
**参数说明：**
- 当 tooltipPosition 值为空或值为 "absolute" 时，tooltip 会随鼠标移动，且不会有小三角；当 tooltipPosition 值为 "relative" 时，tooltip 的位置相对于 *$(targetNode)* 固定，不会随鼠标移动，而且会有小三角；
- 当 existedContentId 为空时，tooltip 里的内容是 content，当 existedContentId 不为空时，不管 content 是否为空，tooltip 里的内容都是 existedContentId；
- tooltip 里小三角不是用伪元素画出来的，而是一个 class 为 arw 的 span 元素： **&lt;span class="arw">**，通过设置 arw 的 		  border 来实现小三角效果；
- tooltip 的样式代码如下：

  ```css
  position: absolute;
  z-index: 10000;
  display: none;
  padding: 10px 15px;
  background-color: rgba(200,200,200,0.7);
  font-size: 14px;
  line-height: 24px;
  color: #000;
  opacity: 1;
  border-radius: 3px;
  ```
- 小三角 arw 的样式代码为：

  ```css
  display: inline-block;
  position: absolute;
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 6px;
  border-color: transparent;
  border-top-color: rgba(200,200,200,0.7);
  ```

## 四、各参数用法示例

1. 默认用法（Tooltip 出现在上侧）

   ```html
   <div id="test1" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test1").easyTooltip({
		//tooltipDir: "top",//默认是 top
		content: "<span style='color:#fe8e14;'>I am </span><span style='color:red;'>a sample.</span>"
	});
   </script>
   ```
   
2. Tooltip 出现在右侧

   ```html
   <div id="test2" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test2").easyTooltip({
		tooltipDir: "right",
		content: "I am a sample.",
		tooltipPosition: ''
	});
   </script>
   ```
   
3. Tooltip 出现在下侧

   ```html
   <div id="test3" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test3").easyTooltip({
		tooltipDir: "bottom",
		content: "I am a sample.",
		tooltipPosition: 'absolute'
	});
   </script>
   ```
   
4. Tooltip 出现在左侧

   ```html
   <div id="test4" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test4").easyTooltip({
		tooltipDir: "left",
		content: "I am a sample."
	});
   </script>
   ```
   
5. existedContentId 属性的使用

   ```html
   <div id="test5" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test5").easyTooltip({
		content: "Test Content.",/*设置了也没用，会被 $('#existedContentId').html() 替换*/
		existedContentId: "existedContentId"
	});
   </script>
   ```
   
6. clickRemove 属性的使用

   ```html
   <div id="test6" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test6").easyTooltip({
		content: "I am a sample.",
		clickRemove: true //触发 click 时间之后 Tooltip 会隐藏
	});
   </script>
   ```
   
7. xOffset/yOffset 属性的使用

   ```html
   <div id="test7" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test7").easyTooltip({
		xOffset: 100,     
    	yOffset: 100,
		tooltipDir: "right",
		content: "I am a sample."
	});
   </script>
   ```
   
8. Tooltip 样式属性的使用

   ```html
   <div id="test8" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test8").easyTooltip({
		content: "I am a sample.",
		/************ tooltip 样式参数 ************/
		defaultRadius: "10px",
		tooltipZindex: 10000,
		tooltipPadding: "20px 30px",
		tooltipBgColor: "rgba(0,0,0,0.7)",
		tooltipFtSize: "18px",
		tooltipLineHeight: "27px",
		tooltipFtColor: "#fe8e14",
		tooltipOpacity: 1,
		tooltipArwBorderWidth: 10
	});
   </script>
   ```
   
9. Tooltip 固定在上侧

   ```html
   <div id="test9" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test9").easyTooltip({
		tooltipPosition: 'relative',
		//tooltipDir: "top",
		content: "I am a sample."
	});
   </script>
   ```
   
10. Tooltip 固定在右侧

   ```html
   <div id="test10" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test10").easyTooltip({
		tooltipPosition: 'relative',
		tooltipDir: "right",
		content: "I am a sample."
	});
   </script>
   ```
   
11. Tooltip 固定在下侧

   ```html
   <div id="test11" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test11").easyTooltip({
		tooltipPosition: 'relative',
		tooltipDir: "bottom",
		content: "I am a sample."
	});
   </script>
   ```
   
12. Tooltip 固定在左侧

   ```html
   <div id="test12" class="item">Hover Me!</div>
   
   <script type="text/javascript">
    $("#test12").easyTooltip({
		tooltipPosition: 'relative',
		tooltipDir: "left",
		content: "I am a sample."
	});
   </script>
   ```
   
13. 改变 Tooltip 小三角的大小

   ```html
   <div id="test13" class="item">Hover Me!</div>
   
   <script type="text/javascript">
  	$("#test13").easyTooltip({
		tooltipPosition: 'relative',
		content: "I am a sample.",
		/************ tooltip 样式参数 ************/
		tooltipArwBorderWidth: 12
	});
   </script>
   ```
      
## 五、easyTooltip 示例

[Demo](https://alvinyw.github.io/Blog/DsTooltip/easyTooltip.html)

## 六、easyTooltip 的优缺点

### 优点：
- 提供的参数比较全，可定制化程度较高；

### 缺点：
- 仅支持 hover 出现提示内容，暂时不支持 click 触发；
- 依赖 jquery;