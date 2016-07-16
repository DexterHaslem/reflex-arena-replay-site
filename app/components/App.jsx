import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import FileListTable from './FileListTable';
import * as actions from '../actions/appActions';
/*
 <tr>
 <th>{{ replay.filename }}</th>
 <th>{{ "%s/%s/%s - %s:%s"|format(replay.time.month, replay.time.day, replay.time.year, replay.time.hour, replay.time.minute)}}</th>
 <th>{{ replay.size }}</th>
 <th><a href="{{ replay.href }}">Download</a></th>
 </tr>
 */
class App extends React.Component {
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        //console.log(this.props);
        this.props.getFiles();
    }

    // cant be static, react wants instance method here
    //noinspection JSMethodCanBeStatic
    render(){
        const files = null;
        return (
            <div>
                <div className="page-header">
                <h1>fragged.online Reflex demos</h1>
                </div>

                <FileListTable files={files} />
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