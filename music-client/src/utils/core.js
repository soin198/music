import qs from "qs";

export function paramsSerializer(params) {
  if (params == null) {
    return '';
  }
  params = Object.keys(params).reduce((prev, key) => {
    const value = params[key];
    if (value != null && value !== '') {
      prev[key] = value;
    }
    return prev;
  }, {});
  return qs.stringify(params, {
    indices: false,
    allowDots: true,
    skipNulls: true
  });
}

export function uuid() {
  return "a" + "xxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function(c) {
    let r = Math.random() * 16 | 0, v = c === "x" ? r : (r & 0x3 | 0x8)
    return v.toString(16)
  })
}

export function formatFloat(value,pos){
  return Math.round(value * Math.pow(10, pos))/Math.pow(10, pos);
}
