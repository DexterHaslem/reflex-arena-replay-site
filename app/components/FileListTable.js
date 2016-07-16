/**
 * Created by Dexter on 7/16/2016.
 */
import React, { PropTypes } from 'react';
import * as ReactBootstrapTable from 'react-bootstrap-table';

const FileListTable = ({files}) => {
    return (<div>
        <BootstrapTable data={files}
                        striped={true}
                        hover={true}
                        condensed={true}
                        pagination={true}
                        search={true}>
            <TableHeaderColumn dataField="id" isKey={true} dataAlign="center" dataSort={true}>Product ID</TableHeaderColumn>
            <TableHeaderColumn dataField="filename" dataSort={true}>Name</TableHeaderColumn>
            <TableHeaderColumn dataField="size">Size</TableHeaderColumn>
            <TableHeaderColumn dataField="time">Time</TableHeaderColumn>
            <TableHeaderColumn dataField="href">Download</TableHeaderColumn>
        </BootstrapTable>
    </div>);
};

export default FileListTable;