export async function fetcher(
  url = '',
  method: string,
  accessToken = '',
  data = {}
) {
  // Default options are marked with *
  const response = await fetch(url, {
    method: method, // *GET, POST, PUT, DELETE, etc.
    headers: {
      'Content-Type': 'application/json',
      mode: 'no-cors', // no-cors, *cors, same-origin
      Authorization: `Bearer ${accessToken}`,
    },
    body: JSON.stringify(data), // body data type must match "Content-Type" header
  });
  return response.json(); // parses JSON response into native JavaScript objects
}
