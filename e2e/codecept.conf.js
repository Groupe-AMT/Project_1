const { setHeadlessWhen } = require('@codeceptjs/configure');

// turn on headless mode when running with HEADLESS=true environment variable
// export HEADLESS=true && npx codeceptjs run
setHeadlessWhen(process.env.HEADLESS);

exports.config = {
  tests: './*_test.js',
  output: './output',
  helpers: {
    Puppeteer: {
      url: 'http://172.18.0.4',
      show: true,
      windowSize: '1200x900'
    }
  },
  include: {
    I: './steps_file.js',
    "Login": './pages/Login.js',
    "SendQuestion": './pages/SendQuestion.js',
    "Register": './pages/Register.js',
    "Profil": './pages/Profil.js',
    "ChangeInfo": './pages/ChangeInfo.js',
    "ChangePassword": './pages/ChangePassword.js',
    "Questions": './pages/Questions.js',
    "Question": './pages/Question.js'
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
