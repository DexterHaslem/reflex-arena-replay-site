import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import FileListTable from './FileListTable';

/*
 <tr>
 <th>{{ replay.filename }}</th>
 <th>{{ "%s/%s/%s - %s:%s"|format(replay.time.month, replay.time.day, replay.time.year, replay.time.hour, replay.time.minute)}}</th>
 <th>{{ replay.size }}</th>
 <th><a href="{{ replay.href }}">Download</a></th>
 </tr>
 */
const App = ({files}) => {

    return (
        <div>
            <div className="page-header">
                <h1>fragged.online Reflex demos</h1>
            </div>

            <FileListTable files={files} />
        </div>
    );
};

const mapStateToProps = state => {
    return { count: state };
};

const mapDispatchToProps = dispatch => {
    return {};
};


export default connect(mapStateToProps, mapDispatchToProps)(App);