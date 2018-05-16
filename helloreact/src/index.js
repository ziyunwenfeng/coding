import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Article from './Article'
import User from './pages/User'
import registerServiceWorker from './registerServiceWorker';
import {BrowserRouter,HashRouter,Route } from 'react-router-dom'

// ReactDOM.render(<Article />, document.getElementById('root'));
ReactDOM.render(
    <HashRouter>
        <div>
        <Route path="/" component = {User}/>
        <Route path="/article" component = {Article}/>
        </div>
    </HashRouter>,
    document.getElementById('root'));

registerServiceWorker();
