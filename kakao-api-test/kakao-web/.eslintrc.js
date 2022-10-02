module.exports = {
  root: true,
  parserOptions: {
    parser: '@babel/eslint-parser',
  },
  env: {
    node: true,
    browser: true
  },
  extends: [
    // 'plugin:vue/essential', // Use this if you are using Vue.js 2.x.
    'plugin:vue/vue3-recommended', // able to use vue-cli eslint
    'eslint:recommended',  // able to use eslint rules (https://eslint.org/docs/rules/)
  ],
  rules: {
    /* eslint:recommended */ 
    'semi': ['error', 'always'],
    'indent': ['error', 2],
    // 'no-mixed-spaces-and-tabs': "error",
    'no-unsafe-finally': ['error'],
    'no-return-assign': ['error'],
    'space-before-function-paren': ['error', {
      'anonymous': 'always', 
      'named': 'never', 
      'asyncArrow': 'always'
    }],
    'space-before-blocks': ['error'],
    'keyword-spacing': ['error', { before: true, after: true }],
    'object-curly-spacing': ['error', 'always'],
    'comma-spacing': ['error', { before: false, after: true }],
    'quotes': ['error', 'single'],
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',

    'vue/max-attributes-per-line': ['error', {
      'singleline': {
        'max': 2
      },
      'multiline': {
        'max': 1
      }
    }],

    /* plugin:vue/vue3-essential */ 
    'vue/html-closing-bracket-newline': ['error', {
      'singleline': 'never',
      'multiline': 'always'
    }],
    'vue/html-closing-bracket-spacing': ['error'],
    'vue/html-end-tags': ['error'],
    'vue/html-indent': ['error', 2],
    'vue/html-quotes': ['error'],
    'vue/html-self-closing': ['error', {
      'html': {
        'void': 'always',
        'normal': 'never'
      }
    }]
  },
};