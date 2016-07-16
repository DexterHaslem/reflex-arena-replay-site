exports.config = {
  hot: true,

  files: {
    javascripts: {
      joinTo: {
        'app.js': /^app/,
        'vendor.js': /^(?!app)/
      }
    },
    stylesheets: { joinTo: 'app.css' }
  },
  npm: {
    styles: {bootstrap: [
        'dist/css/bootstrap.min.css',
        'dist/css/bootstrap-theme.min.css'
    ]}
  },
  plugins: {
    babel: { presets: ['es2015', 'react'] }
  }
};
