import React, { Component } from 'react';
import logo from './logo.svg';
import fetch from 'isomorphic-fetch'
import './App.css';

// import CommentList from './CommentList'
// import CommentInput from './CommentInput'
import ButtonActions from './actions/ButtonActions';


class App extends Component {
  constructor(){
    super()
    this.state = {
      comment : []
    }
  }
  componentWillMount(){
    // ajax.get('http://localhost:8899/user/users',(userData)=>{
      
    // })
  }
  handle(e) {
    console.log(e.target.innerHTML)
    console.log(this)
  }
  componentDidMount(){
    // console.log("``````getAjax``````````")
    // this.getAjax()
    // console.log("``````saveAjax2``````````")
    // this.getAjax2()
    // this.saveAjax()
  }
  render() {
    const word = false;
    
    // const userElem = []
    // for (let u of users) {
    //   userElem.push(
    //     <div>
    //       <div>name : {u.userName}</div>
    //       <div>age : {u.age}</div>
    //     </div>
    //   )
    // }
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title" onClick={this.handle.bind(this)}>Hello React {word ? 124 : null} </h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <button onClick = {this.fluxTest.bind(this)}>flux</button>
        {/* <CommentInput onSubmit={this.handleSubmit.bind(this)}/>
        <CommentList comment={this.state.comment}/> */}
      </div>
    );
    
  }
  fluxTest(){
    let data = ButtonActions.fluxDemo()
    console.log("-----",data)
  }
  handleSubmit(data){
    console.log(data)
    this.state.comment.push(data);
    this.setState({
      comment:this.state.comment
    })
  }
  getAjax(){
    console.log("-----------------------")
    let myHeaders = new Headers({
      'Access-Control-Allow-Origin': '*',
       'Content-Type': 'text/plain'
  });
    // fetch('aaa',{
      fetch('http://localhost:8899/user/users',{
      method : 'GET',
      mode : 'cors',
      headers : myHeaders,
      cache : 'default'
    }).then((res)=>{
      console.log("-------res--------",res)
      if(res.ok){
        console.log("-------res.ok--------",res)
        res.text().then((data)=>{
          console.log("-------res.text--------")
          console.log(data)
        })
      }
    }).catch((res)=>{
      console.log("-------catch--------")
      console.log(res.status)
    })
  }
  getAjax2(){
    console.log("-----getAjax2---------")
    let myHeaders = new Headers({
      'Access-Control-Allow-Origin': '*',
       'Content-Type': 'application/json'
  });
    // fetch('aaa',{
      let url = 'http://localhost:8899/user/getUser'+'?id=1'
      fetch(url,{
      method : 'GET',
      mode : 'cors',
      headers : myHeaders,
      cache : 'default'
    }).then((res)=>{
      console.log("-------res--------",res)
      if(res.ok){
        console.log("-------res.ok--------")
        res.text().then((data)=>{
          console.log("-------res.data--------")
          console.log(data)
        })
      }
    }).catch((res)=>{
      console.log("-------catch--------")
      console.log(res.status)
    })
  }

  saveAjax(){
    let formData = new FormData()
    formData.append('id','1')
    formData.append("id","1")
    formData.append("name","tang")
    formData.append("pwd","166")
    let myHeaders = new Headers({
      'Access-Control-Allow-Origin': '*',
       'Content-Type': 'application/json'
  });
    // console.log(JSON.stringify({
    //   id : '1',
    //   name : 'tangwenfeng',
    //   pwd : '1234567'
    // }))
    console.log("formdata",formData)
    fetch('http://localhost:8899/user/saveU',{
      method : 'POST',
      headers : myHeaders,
      // body : formData
      body : JSON.stringify({
        id : '1',
        name : 'tangwenfeng',
        pwd : 'pwd'
      })
    }).then((res)=>{
      console.log("ddddddd",res)
      if(res.ok){
        // res.json();
      }
      // return res.json()
    })
  }
  
}

export default App;
