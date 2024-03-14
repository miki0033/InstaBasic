export const checkValidity = (payload: {}): boolean => {
	let result = true;
	for (const data in payload) {
		if (!data) {
			result = false;
		}
	}
	return result;
};
