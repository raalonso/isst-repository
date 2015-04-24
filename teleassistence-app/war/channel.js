  	var myModule = Object.create({
    sendMessage : function() {
      var xhr = new XMLHttpRequest();
      xhr.open('POST', '/send-message', true);
      xhr.send();
    },
    initChannel : function() {
      var channel = new goog.appengine.Channel("${token}");
      var socket = channel.open();
      socket.onopen = channelHandler.onOpened;
      socket.onmessage = channelHandler.onMessage;
      socket.onerror = channelHandler.onError;
      socket.onclose = channelHandler.onClose;
    }});
    
    document.addEventListener('DOMContentLoaded',
        myModule.initChannel, false);