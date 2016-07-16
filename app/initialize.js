import ReactDOM from 'react-dom';
import React from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import reducer from './reducers';
import App from 'components/App';

//const store = createStore(reducer, applyMiddleware(thunk));
const store = createStore(reducer, [],
    compose(applyMiddleware(thunk),
            window.devToolsExtension ? window.devToolsExtension() : f => f));

const load = () => {
  ReactDOM.render(
    <Provider store={store}>
      <App />
    </Provider>,
    document.querySelector('#app')
  );
};

if (document.readyState !== 'complete') {
  document.addEventListener('DOMContentLoaded', load);
} else {
  load();
}
