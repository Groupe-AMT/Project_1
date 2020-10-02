const { setHeadlessWhen } = require('@codeceptjs/configure');

// turn on headless mode when running with HEADLESS=true environment variable
// export HEADLESS=true && npx codeceptjs run
setHeadlessWhen(process.env.HEADLESS);

exports.config = {
  tests: './*_test.js',
  output: './output',
  helpers: {
    Puppeteer: {
      url: 'http://localhost',
      show: true,
      windowSize: '1200x900'
    }
  },
  include: {
    I: './steps_file.js',
    "Login": './pages/Login.js',
    "SendQuestion": './pages/SendQuestion.js',
    "Register": './pages/Register.js'
  },
  bootstrap: null,
  mocha: {},
  name: 'CodeceptJS',
  plugins: {
    retryFailedStep: {
      enabled: true
    },
    screenshotOnFail: {
      enabled: true
    }
  }
}