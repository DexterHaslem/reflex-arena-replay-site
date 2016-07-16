/**
 * Created by Dexter on 7/16/2016.
 */

import {GET_FILES} from '../constants/actionTypes';

export function getFiles() {

    return dispatch => {

        // todo: ajax call

        return dispatch({
            type: GET_FILES,
            payload: [{
                "filename": 'filename',
                "size": 'size',
                "time": String(new Date()),
                "href": 'href'
            }]
        });
    };
}