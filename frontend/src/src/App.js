import React from 'react';
import ReactDOM from 'react-dom';
import PropTypes from 'prop-types';
import './css/Book.css';
import './css/App.css'
import './book';
import { Outlet, Link, NavLink ,BrowserRouter,useNavigate,Routes, Route } from 'react-router-dom';
import Login from './view/login';
import TopNavigation from './component/TopNavigation';
import BookDetail from "./view/bookdetail";
import {GlobalProvider} from "./utils/GlobalContext";
const bookList = require('./book');

// 然后你可以在这里使用books数组来显示书籍列表

function App () {
  return (

        <Login/>


  );
}


export default App;
