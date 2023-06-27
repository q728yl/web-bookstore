const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = function (app) {
    app.use(createProxyMiddleware('/api',
        {
            target: 'http://10.180.164.50:8005',
            changeOrigin: true,
            pathRewrite: { '^/api': '' }
        }
    ))
}