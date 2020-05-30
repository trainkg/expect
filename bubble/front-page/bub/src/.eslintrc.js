module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint',   //ESLint 默认使用Espree作为其解析器，你可以在配置文件中指定一个不同的解析器，eg:babel-eslint
    sourceType: 'module'
  },
  env: {
    browser: true,
    node: true,
    es6: true,
  },
  extends: ['plugin:vue/recommended', 'eslint:recommended'],

  // add your custom rules here
  //it is base on https://github.com/vuejs/eslint-config-vue
  rules: {
    //元素有多个特性时，每个特性独占一行的验证   https://github.com/vuejs/eslint-plugin-vue/blob/master/docs/rules/max-attributes-per-line.md
    "vue/max-attributes-per-line": [2, {   //"off" 或 0 - 关闭规则,"warn" 或 1 - 开启规则，使用警告级别的错误：warn (不会导致程序退出),"error" 或 2 - 开启规则，使用错误级别的错误：error (当被触发的时候，程序会退出)
      "singleline": 10,                    //The number of maximum attributes per line when the opening tag is in a single line. Default is 1.
      "multiline": {                       //The max number of attributes per line when the opening tag is in multiple lines. Default is 1.
        "max": 1,                          //This can be { multiline: 1 } instead of { multiline: { max: 1 }} if you don't configure allowFirstLine property.
        "allowFirstLine": false            //If true, it allows attributes on the same line as that tag name. Default is false.
      }
    }],
    //组件 name 属性必须小写连字符形式         https://github.com/vuejs/eslint-plugin-vue/blob/master/docs/rules/name-property-casing.md
    "vue/name-property-casing": ["error", "kebab-case"],   //"PascalCase" (default) ... Enforce the name property to Pascal case;"kebab-case" ... Enforce the name property to kebab case.
    'accessor-pairs': 2,                   //强制getter/setter成对出现在对象中,该规则强制一种编码风格：对于每个属性，如果定义了setter，也必须定义相应的 getter
    'arrow-spacing': [2, {                 //要求箭头函数的箭头之前或之后有空格
      'before': true,
      'after': true
    }],
    'block-spacing': [2, 'always'],       //该规则强制在左花括号和同一行上的下一个 token 之间有一致的空格。该规则同样强制右花括号和在同一行的前一个 token 之间有一致的空格。"always" (more) 要求使用一个或多个空格."never" 禁用空格
    'brace-style': [2, '1tbs', {          //大括号风格要求～这个比较复杂，具体看这里吧 https://cn.eslint.org/docs/rules/brace-style
      'allowSingleLine': true             //"allowSingleLine": true (默认 false) 允许块的开括号和闭括号在 同一行
    }],
    'camelcase': [0, {                    //0-属性命名规则可以不使用驼峰命名法
      'properties': 'always'
    }],
    'comma-dangle': [2, 'never'],         //要求或禁止使用拖尾逗号。"never" (默认) 禁用拖尾逗号；"always" 要求使用拖尾逗号；"always-multiline" 当最后一个元素或属性与闭括号 ] 或 } 在 不同的行时，要求使用拖尾逗号；当在 同一行时，禁止使用拖尾逗号；"only-multiline" 当最后一个元素或属性与闭括号 ] 或 } 在 不同的行时，允许（但不要求）使用拖尾逗号；当在 同一行时，禁止使用拖尾逗号。
    'comma-spacing': [2, {                //强制在逗号周围使用空格
      'before': false,                    //"before": false (默认) 禁止在逗号前使用空格；"before": true 要求在逗号前使用一个或多个空格；
      'after': true                       // "after": true (默认) 要求在逗号后使用一个或多个空格；"after": false 禁止在逗号后使用空格
    }],
    'comma-style': [2, 'last'],           //逗号风格规则强制逗号分隔列表使用一致的风格，"last" (默认) 要求逗号放在数组元素、对象属性或变量声明之后，且在同一行；"first" 要求逗号放在数组元素、对象属性或变量声明之前，且在同一行。该规则还接受一个额外的 exceptions 对象。具体：http://eslint.cn/docs/rules/comma-style
    'constructor-super': 2,               //验证es6构造函数中 super() 的调用     e.g:https://cn.eslint.org/docs/rules/constructor-super
    'curly': [2, 'multi-line'],           //要求遵循大括号约定."multi-line":允许在单行中省略大括号，而if、else if、else、for、while 和 do，在其他使用中依然会强制使用大括号。实现如上定制     http://eslint.cn/docs/rules/curly
    'dot-location': [2, 'property'],      //允许你在成员表达式中的点操作符之前或之后放置一个换行符.如果它的值是 "object"，表达式中的点号操作符应该和对象部分在同一行。默认是 "object"。如果它的值是 "property"，表达式中的点号操作符应该和属性在同一行。
    'eol-last': 2,                        //非空文件末尾至少存在一行空行（或缺少换行）."always" (默认) 强制使用换行 (LF)."never" 强制文件末尾不要有换行符
    'eqeqeq': [2, 'always', {'null': 'ignore'}],          //旨在消除非类型安全的相等操作符.要求使用 === 和 !==     http://eslint.cn/docs/rules/eqeqeq
    'generator-star-spacing': [2, {       //强制 generator 函数中 * 号周围有空格
      'before': true,                     //before 强制在 * 和 function 关键字之间有空格。如果设置为 true ，要求有空格，否则不允许有空格。在对象文本的缩写方法中，*之前的空格不会被检查，因为它们缺少 function 关键字。
      'after': true                       //after 强制在 * 和函数名之间有空格 (或匿名 generator 函数的左括号)。如果设置为 true，要求有空格，否则不允许有空格。
    }],
    'handle-callback-err': [2, '^(err|error)$'], //这个模式期望一个 Error 对象或 null 作为回调的第一个参数    https://cn.eslint.org/docs/rules/handle-callback-err
    'indent': [2, 2, {                    //该规则旨在强制使用一致的缩进风格。默认是 4个空格.这里是 2 个空格缩进 http://eslint.cn/docs/rules/indent
      'SwitchCase': 1                     //"SwitchCase" (默认：0) 强制 switch 语句中的 case 子句的缩进级别
    }],
    'jsx-quotes': [2, 'prefer-single'],   //在 JSX 属性中使用一致的单引号或双引号。"prefer-double" (默认) 强制所有不包含双引号的 JSX 属性值使用双引号。"prefer-single" 强制所有不包含单引号的 JSX 属性值使用单引号。
    'key-spacing': [2, {
      'beforeColon': false,               //false:禁止在对象字面量的键和冒号之间存在空格
      'afterColon': true                  //true: 要求在对象字面量的冒号和值之间存在至少有一个空格
    }],
    'keyword-spacing': [2, {              //该规则强制关键字和类似关键字的符号周围空格的一致性:as、async、await、break、case、catch、class、const、continue、debugger、default、delete、do、else、export、extends、finally、for、from、function、get、if、import、in、instanceof、let、new、of、return、set、static、super、switch、this、throw、try、typeof、var、void、while、with 和 yield
      'before': true,                     //true (默认) 要求在关键字之前至少有一个空格
      'after': true                       //true (默认) 要求在关键字之后至少有一个空格
    }],
    'new-cap': [2, {                      //要求构造函数首字母大写
      'newIsCap': true,                   //true(默认) 要求调用 new 操作符时有首字母大小的函数。
      'capIsNew': false                   //true(默认) 要求调用首字母大写的函数时有 new 操作符。false:允许调用首字母大写的函数时没有 new 操作符。
    }],
    'new-parens': 2,                      //要求调用无参构造函数时带括号
    'no-array-constructor': 2,            //禁止使用 Array 构造函数
    'no-caller': 2,                       //禁用 caller 或 callee (no-caller)
    'no-console': 'off',
    'no-class-assign': 2,                 //不允许修改类声明的变量
    'no-cond-assign': 2,                  //禁止在条件语句中出现赋值操作符
    'no-const-assign': 2,                 // 禁止修改 const 声明的变量
    'no-control-regex': 0,                // 禁止在正则表达式中使用控制字符 ：new RegExp("\x1f")
    'no-delete-var': 2,                   // 禁止删除变量。delete 的目的是删除对象的属性。使用 delete 操作删除一个变量可能会导致意外情况发生。
    'no-dupe-args': 2,                    // 禁止在 function 定义中出现重复的参数
    'no-dupe-class-members': 2,           // 不允许类成员中有重复的名称
    'no-dupe-keys': 2,                    // 禁止在对象字面量中出现重复的键
    'no-duplicate-case': 2,               // 禁止重复 case 标签
    'no-empty-character-class': 2,        // 禁止在正则表达式中出现空字符集
    'no-empty-pattern': 2,                // 禁止使用空解构模式
    'no-eval': 2,                         // 禁用 eval()
    'no-ex-assign': 2,                    // 禁止对 catch 子句中的异常重新赋值
    'no-extend-native': 2,                // 禁止扩展原生对象。 http://eslint.cn/docs/rules/no-extend-native
    'no-extra-bind': 2,                   // 禁止不必要的函数绑定
    'no-extra-boolean-cast': 2,           // 禁止不必要的布尔类型转换
    'no-extra-parens': [2, 'functions'],  // 禁止冗余的括号。"functions" 只在 函数表达式周围禁止不必要的圆括号
    'no-fallthrough': 2,            // 禁止 case 语句落空。
    'no-floating-decimal': 2,       // 禁止浮点小数。
    'no-func-assign': 2,            // 禁止对 function 声明重新赋值
    'no-implied-eval': 2,           // 禁用隐式的eval()
    'no-inner-declarations': [2, 'functions'],  // 禁止在嵌套的语句块中出现变量或 function 声明
    'no-invalid-regexp': 2,         // 禁止在 RegExp 构造函数中出现无效的正则表达式
    'no-irregular-whitespace': 2,   // 禁止不规则的空白
    'no-iterator': 2,               // 禁用迭代器。__iterator__ 属性曾是 SpiderMonkey 对 JavaScript 的扩展，被用来创建自定义迭代器，兼容JavaScript的 for in 和 for each。然而，这个属性现在废弃了，所以不应再使用它。
    'no-label-var': 2,              // 禁用与变量同名的标签。
    'no-labels': [2, {              // 禁用标签语句。
      'allowLoop': false,           // "allowLoop" (boolean，默认是 false) - 如果这个选项被设置为 true，该规则忽略循环语句中的标签。
      'allowSwitch': false          // "allowSwitch" (boolean，默认是 false) - 如果这个选项被设置为 true，该规则忽略 switch 语句中的标签。
    }],
    'no-lone-blocks': 2,            // 禁用不必要的嵌套块。
    'no-mixed-spaces-and-tabs': 2,  // 禁止使用 空格 和 tab 混合缩进。
    'no-multi-spaces': 2,           // 禁止出现多个空格。
    'no-multi-str': 2,              // 禁止多行字符串。该规则是为了防止多行字符串的使用。
    'no-multiple-empty-lines': [2, {   // 不允许多个空行。"max" (默认为 2) 强制最大连续空行数。"maxEOF" 强制文件末尾的最大连续空行数。"maxBOF" 强制文件开始的最大连续空行数。
      'max': 1
    }],
    'no-native-reassign': 2,        // 不能重写native对象
    'no-negated-in-lhs': 2,         // in 操作符的左边不能有!
    'no-new-object': 2,             // 禁止使用 Object 构造函数。 var myObject = new Object();然而，这与使用更为简洁的字面量没有什么区别：var myObject = {};
    'no-new-require': 2,            // 不允许 new require. https://cn.eslint.org/docs/rules/no-new-require
    'no-new-symbol': 2,             // 禁止 Symbolnew 操作符和 new 一起使用
    'no-new-wrappers': 2,           // 禁止原始包装实例. https://cn.eslint.org/docs/rules/no-new-wrappers
    'no-obj-calls': 2,              // 禁止将全局对象当作函数进行调用。该规则禁止将 Math、JSON 和 Reflect 对象当作函数进行调用。var math = Math();var json = JSON();var reflect = Reflect();
    'no-octal': 2,                  // 禁用八进制字面量。
    'no-octal-escape': 2,           // 禁止在字符串字面量中使用八进制转义序列。
    'no-path-concat': 2,            // 当使用 _dirname 和 _filename 时不允许字符串拼接。 （非常重要，看这里：https://cn.eslint.org/docs/rules/no-path-concat）
    'no-proto': 2,                  // 禁用__proto__。__proto__ 属性在 ECMAScript 3.1 中已经被弃用，并且不应该在代码中使用。使用 getPrototypeOf 方法替代 __proto__。
    'no-redeclare': 2,              // 禁止重新声明变量
    'no-regex-spaces': 2,           // 禁止正则表达式字面量中出现多个空格。eg: var re = /foo {3}bar/;(yes) var re = /foo   bar/;(no)
    'no-return-assign': [2, 'except-parens'],  // 禁止在返回语句中赋值。except-parens（默认）：禁止出现赋值语句，除非使用括号把它们括起来。always：禁止所有赋值
    'no-self-assign': 2,              // 禁止自身赋值。eg：foo = foo;
    'no-self-compare': 2,             // 禁止自身比较。变量与其自身进行比较通常来说是一个错误，要么是打字错误要么是重构错误。它都会给读者造成困扰并且可能会引入运行错误。唯一肯能会对变量自身做比较时候是当你在测试变量是否是 NaN。然而，在这种情况下，更适合使用 typeof x === 'number' && isNaN(x) 或者 Number.isNaN ES2015 函数 而不是变量自身比较。
    'no-sequences': 2,                // 不允许使用逗号操作符
    'no-shadow-restricted-names': 2,  // 关键字不能被遮蔽。全局对象的属性值 (NaN、Infinity、undefined)和严格模式下被限定的标识符 eval、arguments 也被认为是关键字。重定义关键字会产生意想不到的后果且易迷惑其他读者。比如：var undefined = "foo";
    'no-spaced-func': 2,            //
    'no-sparse-arrays': 2,          // 禁用稀疏数组。稀疏数组包括很多空位置，经常是由于在数组字面量中使用多个逗号造成的，例如： var items = [,,];
    'no-this-before-super': 2,      // 在构造函数中禁止在调用super()之前使用this或super
    'no-throw-literal': 2,          // 限制可以被抛出的异常。 https://cn.eslint.org/docs/rules/no-throw-literal
    'no-trailing-spaces': 2,        // 禁用行尾空白
    'no-undef': 2,                  // 禁用未声明的变量。此规则可帮助你定位由变量漏写、参数名漏写和意外的隐式全局变量声明所导致的潜在引用错误（比如，在 for 循环语句中初始化变量忘写 var 关键字）
    'no-undef-init': 2,             // 不允许初始化变量值为 undefined
    'no-unexpected-multiline': 2,   // 禁止使用令人困惑的多行表达式
    'no-unmodified-loop-condition': 2, // 禁用一成不变的循环条件 (no-unmodified-loop-condition)。循环条件中的变量在循环中是要经常改变的。如果不是这样，那么可能是个错误。
    'no-unneeded-ternary': [2, {   // 禁止可以表达为更简单结构的三元操作符
      'defaultAssignment': false   // true (默认) 允许条件表达式作为默认的赋值模式。false 禁止条件表达式作为默认的赋值模式
    }],
    'no-unreachable': 2,          // 禁止在 return、throw、continue 和 break 语句后出现不可达代码
    'no-unsafe-finally': 2,       // 禁止在 finally 语句块中出现控制流语句
    'no-unused-vars': [2, {       // 禁止未使用过的变量。此规则旨在消除未使用过的变量，方法和方法中的参数名，当发现这些存在，发出警告。
      'vars': 'all',              // all 检测所有变量，包括全局环境中的变量。这是默认值。
      'args': 'none'              // after-used - 最后一个参数必须使用。如：一个函数有两个参数，你使用了第二个参数，ESLint 不会报警告。all - 所有命名参数必须使用。none - 不检查参数。
    }],
    'no-useless-call': 2,         // 禁用不必要的 .call() 和 .apply()
    'no-useless-computed-key': 2, // 禁止在对象中使用不必要的计算属性
    'no-useless-constructor': 2,  // 禁用不必要的构造函数
    'no-useless-escape': 0,   // 禁用不必要的转义 配置文件中的 "extends": "eslint:recommended" 属性启用了此规则。对字符串、模板字面量和正则表达式中的常规字符进行转义，不会对结果产生任何影响
    'no-whitespace-before-property': 2, // 禁止属性前有空白
    'no-with': 2,             // 禁用 with 语句
    'one-var': [2, {          // 强制函数中的变量在一起声明或分开声明         https://cn.eslint.org/docs/rules/one-var
      'initialized': 'never'  // "initialized": "never" 要求每个作用域的初始化的变量有多个变量声明
    }],
    'operator-linebreak': [2, 'after', {     // 强制操作符使用一致的换行符风格。"after" 要求把换行符放在操作符后面；"before" 要求把换行符放在操作符前面；"none" 禁止在操作前后有换行符
      'overrides': {            // "overrides" 覆盖对指定的操作的全局设置
        '?': 'before',
        ':': 'before'
      }
    }],
    'padded-blocks': [2, 'never'],   // 要求或禁止块内填充。"always" (默认) 要求块语句和类的开始或末尾有空行。"never" 禁止块语句和类的开始或末尾有空行
    'quotes': [2, 'single', {     //强制使用一致的反勾号、双引号或单引号。"single" 要求尽可能地使用单引号
      'avoidEscape': true,        //"avoidEscape": true 允许字符串使用单引号或双引号，只要字符串中包含了一个其它引号，否则需要转义
      'allowTemplateLiterals': true //"allowTemplateLiterals": true 允许字符串使用反勾号
    }],
    'semi': [2, 'never'],
    'semi-spacing': [2, {     // 强制分号前后有空格。该规则有一个可选项，是个对象，有两个键 before 和 after 对应的值为布尔类型的值 true 或 false。
      'before': false,        // 如果设置 before 为 true，分号之前强制有空格，如果设置为 false，分号之前禁止有空格。
      'after': true           // 如果设置 after 为 true.分号之后强制有空格，如果设置为 false，分号之后禁止有空格。
    }],
    'space-before-blocks': [2, 'always'], // 要求或禁止语句块之前的空格。该规则有一个参数。如果为 "always"，块语句必须总是至少有一个前置空格。如果为"never"，所有的块永远不会有前置空格。如果函数块和关键字块要求不同的空格类型，可以单独传递一个可选配置的对象作为该规则的参数来配置这种情况。(比如：{ "functions": "never", "keywords": "always", classes: "always" } )
    'space-before-function-paren': [2, 'never'], // 要求或禁止函数圆括号之前有一个空格,"always" (默认) 要求在参数的 ( 前面有一个空格。"never"禁止在参数的 ( 前面有空格。
    'space-in-parens': [2, 'never'],  // 禁止或强制圆括号内的空格,
    'space-infix-ops': 2,           // 要求中缀操作符周围有空格。eg：a + b （yes), a+b(no)
    'space-unary-ops': [2, {        // 要求或禁止在一元操作符之前或之后存在空格
      'words': true,                // words - 适用于单词类一元操作符，例如：new、delete、typeof、void、yield
      'nonwords': false             // 适用于这些一元操作符: -、+、--、++、!、!!
    }],
    'spaced-comment': [2, 'always', {          //要求或禁止在注释前有空白 (space 或 tab)。第一个是个字符串，值为 "always" 或 "never"。默认是 "always"。如果是 "always"，// 或 /* 必须跟随至少一个空白。如果是 "never"，其后不允许有空白。
      'markers': ['global', 'globals', 'eslint', 'eslint-disable', '*package', '!', ',']
    }],
    'template-curly-spacing': [2, 'never'], //强制模板字符串中空格的使用。"never" (默认) - 禁止花括号内出现空格。"always" - 要求花括号内有一个或多个空格。
    'use-isnan': 2,             //要求调用 isNaN()检查 NaN，该规则禁止与 ‘NaN’ 的比较。
    'valid-typeof': 2,          //强制 typeof 表达式与有效的字符串进行比较。对于绝大多数用例而言，typeof 操作符的结果是以下字符串字面量中的一个："undefined"、"object"、"boolean"、"number"、"string"、"function" 和 "symbol"。把 typeof 操作符的结果与其它字符串进行比较，通常是个书写错误。
    'wrap-iife': [2, 'any'],    //需要把立即执行的函数包裹起来。"outside" 强制总是包裹 call 表达式。默认是 "outside"。"inside" 强制总是包裹 function 表达式。"any"强制总是包裹，但允许其它风格。
    'yield-star-spacing': [2, 'both'], //// 强制在 yield* 表达式中 * 周围使用空格。before 强制在 yield 和 * 之间有空格。 如果为 true，要求有一个空格，否则禁止有空格。after 强制在 * 和 参数之间有空格。 如果为 true，要求有一个空格，否则禁止有空格。both是前后都有空格的简写
    'yoda': [2, 'never'],       //Yoda 条件被如此命名，是因为在条件判断中字面量在先而变量在第二的位置。它被叫做 Yoda 条件是因为它if ("red" === color)这样读：”红色是颜色”，类似于星球大战中 Yoda 的讲话方式。
    'prefer-const': 2,  //建议使用const
    'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,    //禁用 debugger,当生产环境的时候禁用，
    'object-curly-spacing': [2, 'always', {         // 强制在花括号中使用一致的空格."never" (默认) 不允许花括号中有空格."always" 要求花括号内有空格 (除了 {})
      objectsInObjects: false                       // "objectsInObjects": false 禁止以对象元素开始或结尾的对象的花括号中有空格 (当第一个选项为 always 时生效)
    }],
    'array-bracket-spacing': [2, 'never']           // 指定数组的元素之间要以空格隔开(, 后面)， never参数：[ 之前和 ] 之后不能带空格，always参数：[ 之前和 ] 之后必须带空格
  }
}
