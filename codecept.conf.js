exports.config = {
  tests: './e2e/*_test.js',
  output: './output',
  helpers: {
    Puppeteer: {
      url: 'http://172.17.0.2',
      show: true,
      windowSize: '1200x900'
    }
  },
  include: {
    I: './steps_file.js'
  },
  bootstrap: null,
  mocha: {},
  name: 'Project_1',
  translation: 'fr-FR',
  plugins: {
    pauseOnFail: {},
    retryFailedStep: {
      enabled: true
    },
    tryTo: {
      enabled: true
    },
    screenshotOnFail: {
      enabled: true
    }
  }
}