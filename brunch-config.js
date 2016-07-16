exports.config = {

  files: {
    javascripts: {
      joinTo: {
        'app.js': /^app/,
        'vendor.js': /^(?!app)/
      }
    },
    stylesheets: {
      joinTo: {
        'app.css': /^app/,
        'vendor.css': /^(?!app)/
      }
    }
  },
  npm: {
    styles: {
      bootstrap: [
        'dist/css/bootstrap.min.css',
        'dist/css/bootstrap-theme.min.css'
      ],
      'react-bootstrap-table': [
        'css/react-bootstrap-table.min.css'
      ]},
  },
  plugins: {
    babel: { presets: ['es2015', 'react'] }
  }
};
