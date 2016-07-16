import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import FileListTable from './FileListTable';
import * as actions from '../actions/appActions';

class App extends React.Component {
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.getFiles();
    }

    // cant be static, react wants instance method here
    //noinspection JSMethodCanBeStatic
    render(){
        return (
            <div className="container-fluid">
                <div className="pageHeader">
                    <h1>fragged.online Reflex demos</h1>
                </div>
                <FileListTable files={this.props.files} />

                <h4>
                    <span>Questions or comments? <a href="mailto:dexter.haslem@gmail.com" target="_top">Email</a></span>
                </h4>
            </div>);
    }
}

App.propTypes = {
    files: PropTypes.arrayOf(PropTypes.shape({
        // TODO: final shape
    }))
};

const mapStateToProps = state => {
    return {
        files: state
    };
};

const mapDispatchToProps = dispatch => {
    return bindActionCreators(actions, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(App);