/**
 * Created by Dexter on 7/16/2016.
 */

import { GET_FILES } from 'constants/actionTypes';

export default (state = [], action) => {
  switch (action.type) {
    case GET_FILES:
      // return {
      //     ...state,
      // };
      return action.payload;
    default: return state;
  }
}
