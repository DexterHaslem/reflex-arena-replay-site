/**
 * Created by Dexter on 7/16/2016.
 */
import React, { PropTypes } from 'react';
import * as ReactBootstrapTable from 'react-bootstrap-table';

const FileListTable = ({files}) => {
    const downloadFormat = (cell, row) => {
        return (<a href={cell}>Download</a>);
    };

    const timeFormat = (cell, row) => {
        return String(new Date(cell));
    };
    const options = {
        sizePerPageList: [ 20 ],
        sizePerPage: 20,
        // pageStartIndex: 0,
        // paginationSize: 5
    };
    return (<div>
        <BootstrapTable data={files}
                        striped={true}
                        hover={true}
                        condensed={true}
                        pagination={true}
                        search={true}
                        options={options}>
            <TableHeaderColumn dataField="id" isKey={true} hidden={true}>id</TableHeaderColumn>
            <TableHeaderColumn dataField="filename" dataSort={true}>Name</TableHeaderColumn>
            <TableHeaderColumn dataField="size" dataSort={true}  width="100">Size</TableHeaderColumn>
            <TableHeaderColumn dataField="time" dataSort={true} dataFormat={timeFormat}>Time</TableHeaderColumn>
            <TableHeaderColumn dataField="href" dataFormat={downloadFormat} width="100">Download</TableHeaderColumn>
        </BootstrapTable>
    </div>);
};

FileListTable.propTypes = {
    files: PropTypes.arrayOf(PropTypes.shape({
        // TODO: final shape
    }))
};

export default FileListTable;