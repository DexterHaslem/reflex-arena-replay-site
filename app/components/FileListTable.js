/**
 * Created by Dexter on 7/16/2016.
 */
import React, { PropTypes } from 'react';
//import * as ReactBootstrapTable from 'react-bootstrap-table';
import ReactBootstrapTable from 'react-bootstrap-table';
import {replace, compose} from 'ramda';

const FileListTable = ({files}) => {
    // const downloadFormat = (cell, row) => {
    //     return (<a className="btn btn-primary btn-sm" href={cell}>Download</a>);
    // };

    const timeFormat = (cell, row) => {
        return String(new Date(cell));
    };

    const sizeFormat = (cell, row) => {
        const megs = cell / (1024 * 1024);
        // stupid 2 decimal rounding thing
        const rounded = Math.round(megs * 100) / 100;
        return rounded + ' MB';
    };

    const nameFormat = (cell, row) => {
        // every replay ends with '_0markers.rep' so chop off on UI
        // so searching for eg 'mark' is less stupid
        // NOTE: well haha, this is pointless in regard to search, because
        // search is already handled before formatting so it doesnt help that situation
        // not really a big deal for now
        const name = replace('.rep', '',replace('_0markers', '', cell));
        return (
            <div>
                <a className="btn btn-primary btn-sm" href={row.href}
                   style={{marginRight: "10px"}}>Download</a>
                <label className="control-label">{name}</label>
            </div>);
    };

    const options = {
        sizePerPageList: [ 15 ],
        sizePerPage: 15,
        // pageStartIndex: 0,
        // paginationSize: 5
        clearSearch: true,
        defaultSortName: 'time',
        defaultSortOrder: 'desc'
    };

    return (<div>
        <BootstrapTable data={files}
                        striped={true}
                        hover={true}
                        condensed={true}
                        pagination={true}
                        search={true}
                        multiColumnSearch={true}
                        options={options}>
            <TableHeaderColumn dataField="filename" isKey={true} dataSort={true} dataFormat={nameFormat}>Name</TableHeaderColumn>
            <TableHeaderColumn dataField="size" dataSort={true} width="100" dataFormat={sizeFormat}>Size</TableHeaderColumn>
            <TableHeaderColumn dataField="time" dataSort={true} dataFormat={timeFormat}>Time</TableHeaderColumn>
            {/*<TableHeaderColumn dataField="href" dataFormat={downloadFormat} width="100">Download</TableHeaderColumn>*/}
        </BootstrapTable>
    </div>);
};

FileListTable.propTypes = {
    files: PropTypes.arrayOf(PropTypes.shape({
        // TODO: final shape
    }))
};

export default FileListTable;