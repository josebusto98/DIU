import React, { Component } from 'react';

function name() {

  //document.getElementById("notificationDiv").innerHTML = "" + "<br/>";
  window.location.reload();

};

export default class notificacion extends Component {

    constructor() {
      
        super();
        this.state = {
        eventSource: new EventSource("http://localhost:8080/api/v1/tutorials/new_notification")       
        }       
    }
    //Una vez que el componente ha sido cargado le asociamos el escuchador
      componentDidMount () {  
        this.state.eventSource.onmessage = function(e) {    
        let notification = JSON.parse(e.data);               
        document.getElementById("notificationDiv").innerHTML += notification.text + " a " + new Date(notification.time) + "<br/>";
        setTimeout(name, 6000);
        }
      }
    
    render() {
        return (
             <div id="notificationDiv"></div>
        )
    }

}
