// var localvideo = document.querySelector('video#localVideo');
var nowvideo = document.querySelector('video#nowvideo');
var buttonloacl = document.querySelector('a#buttonloacl');
var buttonremote = document.querySelector('a#buttonremote');
var text = document.querySelector('textarea#text');
var container = document.getElementById("container");
var sum=2*1024*1024;
var pcConfig = {
    'iceServers': [{
        'urls': 'turn:stun.al.learningrtc.cn:3478',
        'credential': "mypasswd",
        'username': "garrylea"
    }]
};
var pcMap = new Map();
var candidateMap = new Map();
var videoMap = new Map();
var divMap = new Map();
var aMap = new Map();
var localStream = null;
// var remoteStreams = null;
function setWhile(){
    pcMap.forEach(function (v,k,arr){
        let size = pcMap.size;
        setChang_bw(v,size+1);
    })

setTimeout(function () {
        setWhile();
    }
    , 5000);
}
function setChang_bw(pc,count) {
    let bw = sum / count;
    let vsender = null
    let senders = pc.getSenders();
    senders.forEach(function (sender) {
        if (sender && sender.track.kind === 'video') {
            vsender = sender;
        }
    });
    let parameters = vsender.getParameters();
    if (!parameters.encodings){
        return;
    }
    parameters.encodings[0].maxBitrate=bw;
    vsender.setParameters(parameters)
        .then(function () { console.log("设置发送码率成功"+bw); })
        .catch(function (reason) { console.log(reason) });
}
try {
    let url = 'wss://' + window.location.host + '/websocket/1';
    websocket = new WebSocket(url);
}catch (e) {
}

buttonloacl.onclick = function () {
    if (!navigator.mediaDevices ||
        !navigator.mediaDevices.getUserMedia) {
        alert("该浏览器不支持,请更换Chrome或者其他支持的浏览器");
        return;
    } else {
        var constraints = {
            audio: true,
            video: true
        };
        navigator.mediaDevices.getUserMedia(constraints)
            .then(getlocalstream)
            .catch(handerlocalstreamErr);
    }
};
buttonremote.onclick = function () {
    senderMessage('join', null, null, null, null);
    setWhile();
}

function handerlocalstreamErr(err) {
    console.error("获取本地媒体流失败", err);
}

function getlocalstream(stream) {
    localStream = stream;
    let div = document.createElement('div');
    div.className='col-xs-6 col-md-3';
    // div.style.backgroundColor = '#110022'
    let a = document.createElement('a');
    a.className='thumbnail';
    a.href='#';
    a.style.backgroundColor = '#110022'
    div.appendChild(a);
    let localvideo = document.createElement('video');
    a.appendChild(localvideo);

    localvideo.id='localvideo';
    localvideo.height='150';
    a.style.width=localvideo.width;
    a.style.height=localvideo.height;
    localvideo.srcObject = stream;
    localvideo.autoplay=true;
    container.appendChild(div);
    buttonremote.disabled = false;
    a.onclick = function () {
        nowvideo.srcObject=localvideo.srcObject;
    };
}

websocket.onopen = function () {
    console.log("建立 websocket 连接...");
};
websocket.onmessage = function (event) {
    var data = JSON.parse(event.data);
    console.log('服务器发来的数据:', data);
    if (data.type === 'joined') {
        console.log("加入房间成功");
        createRTCPeerConnetion(data.sessionid);
    } else if (data.type === 'other_join') {
        console.log("创建RTCPeer");
        createRTCPeerConnetion(data.sessionid);
        let pc = pcMap.get(data.sessionid);
        let options = {
            offerToReceiveAudio: 1,
            offerToReceiveVideo: 1
        }
        pc.createOffer(options)
            .then(function (desc) {
                pc.setLocalDescription(desc);
                senderMessage('offer', desc, data.sessionid, null, null);
            })
            .catch(handerOfferErr);
    } else if (data.type === 'offer') {
        console.log("远方SDP添加到本地,并发送本地awser");
        let pc = pcMap.get(data.sessionid);
        pc.setRemoteDescription(new RTCSessionDescription(JSON.parse(data.data)));
        pc.createAnswer()
            .then(function (desc) {
                pc.setLocalDescription(desc);
                senderMessage('answer', desc, data.sessionid, null, null);
            })
            .catch(handerAnswerErr);
    } else if (data.type === 'answer') {
        console.log("远方AnsweSDP添加到本地,并保存到本地")
        let pc = pcMap.get(data.sessionid);
        pc.setRemoteDescription(new RTCSessionDescription(JSON.parse(data.data)));
    } else if (data.type === 'leaved') {
        pcMap.forEach(function (key, value) {
            value.close();
        });
        pcMap.clear();
        divMap.clear();
        aMap.clear();
        videoMap.forEach(function (key, value) {
            value.srcObject.close();
        });
        videoMap.clear();
        console.log('关闭成功');
    } else if (data.type === 'bye') {
        if (pcMap.has(data.sessionid)) {
            let depc = pcMap.get(data.sessionid);
            pcMap.delete(data.sessionid);
            depc.close();
            depc = null;
        }
        if (videoMap.has(data.sessionid)){
            let video = videoMap.get(data.sessionid);
            videoMap.delete(data.sessionid);
            video.parentNode.removeChild(video);
            video.srcObject=null;
            video=null;
            let div = divMap.get(data.sessionid);
            divMap.delete(data.sessionid);
            div.parentNode.removeChild(div);
            let a = aMap.get(data.sessionid);
            aMap.delete(data.sessionid);
            a.parentNode.remove(a);

        }


        console.log('对方已关闭', data.sessionid);
    } else if (data.type === 'candidate') {
        console.log("添加远方ICEcondidate到本地RTCPeerConnetion", data.sessionid)

        var condidate = new RTCIceCandidate({
            candidate: data.candidate,
            sdpMLineIndex: data.sdpMLineIndex
        })
        var pc = pcMap.get(data.sessionid);
        if (!pc) {
            candidateMap.get(data.sessionid).push(condidate);
        } else {
            let arr = candidateMap.get(data.sessionid);
            while (arr.length > 0) {
                pc.addIceCandidate(arr.pop());
            }
            pc.addIceCandidate(condidate);
        }

    }
}

function handerOfferErr(err) {
    console.error("获取本地offer错误", err);
}

function handerAnswerErr(err) {
    console.error("获取本地Answer错误", err);
}

function createRTCPeerConnetion(sessionid) {

    let pc = new RTCPeerConnection(pcConfig);
    candidateMap.set(sessionid, []);
    pcMap.set(sessionid, pc);
    pc.onicecandidate = function (e) {
        if (e.candidate) {
            console.log("获得ICD,并发送到连接对方")
            senderMessage("candidate", null,
                sessionid,
                e.candidate.candidate, e.candidate.sdpMLineIndex
            );
        }
    };
    pc.ontrack = function (e) {

        let stream = e.streams[0];
        let remotevideo = null;

        if (!videoMap.has(sessionid)){
            let div = document.createElement('div');
            div.className='col-xs-6 col-md-3';
            // div.style.backgroundColor = '#110022';

            let a = document.createElement('a');
            a.className='thumbnail';
            div.appendChild(a);
            a.style.backgroundColor = '#110022'


            remotevideo = document.createElement('video');
            remotevideo.id='video-'+sessionid;
            // localvideo.width='200';
            remotevideo.height='150';
            a.style.width=remotevideo.width;
            a.style.height=remotevideo.height;
            remotevideo.autoplay=true;
            a.appendChild(remotevideo);
            a.href='#';
            container.appendChild(div);
            videoMap.set(sessionid,remotevideo);
            divMap.set(sessionid,div);
            aMap.set(sessionid,a);
            a.onclick = function () {
                nowvideo.srcObject=remotevideo.srcObject;
            };
        }else {
            remotevideo = videoMap.get(sessionid);
        }

        remotevideo.srcObject = stream;

        console.log("获得远程媒体源")
    }
    console.log('绑定本地媒体轨');
    if (localStream) {
        localStream.getTracks().forEach(function (track) {
            pc.addTrack(track, localStream);
        });
    }
//ZWfmcU2brCOtb8qjahhoLNX8BmkH1xXLZ6CD

}


function senderMessage(type, data, sessionid, candidate, sdpMLineIndex) {
    console.log("发送给服务器的数据", type, data, sessionid, candidate, sdpMLineIndex)
    if (websocket) {
        websocket.send(JSON.stringify({
            'group': 'rtc',
            'type': type,
            'sessionid': sessionid,
            'data': data,
            'candidate': candidate,
            'sdpMLineIndex': sdpMLineIndex

        }))
    }

}

