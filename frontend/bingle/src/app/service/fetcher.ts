export async function fetcher(
  url = '',
  method: string,
  accessToken = '',
  data: any = undefined
) {
  const headers: any = {
    'Content-Type': 'application/json',
    mode: 'no-cors', // no-cors, *cors, same-origin
    // cache: 'force-cache',
  };
  if (accessToken) {
    headers['Authorization'] = `Bearer ${accessToken}`;
  }
  const requestInit: any = {
    method: method,
    headers: headers,
  };
  if (data) {
    requestInit['body'] = JSON.stringify(data);
  }
  // Default options are marked with *
  console.log(requestInit);
  const response = await fetch(url, requestInit);
  return response.json(); // parses JSON response into native JavaScript objects
}
