module.exports = {
    devServer: {
        host: '0.0.0.0',
        port: 8088,
        open: true,
        proxy: {
            "/api": {
                target: `http://localhost:9996`,
                ws:true,
                changOrigin:true,
                pathRewrite:{
                    '^/api': '/'
                }
            },
            // "/exa": {
            //     target: `http://192.168.105.22`,
            //     ws:true,
            //     changOrigin:true,
            //     // pathRewrite:{
            //     //     '^/exa': '/'
            //     // }
            // }
            
            
        },
        disableHostCheck: true
    }
   
}