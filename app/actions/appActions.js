/**
 * Created by Dexter on 7/16/2016.
 */

import {GET_FILES} from '../constants/actionTypes';
import {times} from 'ramda';

export function getFiles() {

    // todo: ajax call

    return dispatch => {
        const payload = times(i => ({
            id: i,
            filename: i,
            size: i * 10,
            time: Date.now() - (i * 1000),
            href: 'http://foo.com/asdf/fasdf'
        }), 500);

        return dispatch({
            type: GET_FILES,
            payload
        });
    };
}