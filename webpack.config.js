module.exports = {
    entry: './layers/front-layer/app.js',
    module: {
        rules: [
            {
                test: /\.(js)$/,
                exclude: [
                    /node_modules/,
                    '/layers/front-layer/spec_setup.js',
                ],
            }
        ]
    },
    resolve: {
        extensions: ['*', '.js']
    },
    output: {
        path: __dirname + '/layers/web-layer/dist',
        publicPath: '/',
        filename: 'bundle.js'
    },
    devServer: {
        contentBase: './layers/web-layer'
    }
};
