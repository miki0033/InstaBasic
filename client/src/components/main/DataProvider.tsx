import { Dispatch, ReactNode, createContext, useContext, useReducer } from "react";

// context type
interface IDataContext {
	state: {};
	dispatch: Dispatch<ACTIONTYPE>;
}

// reducer actions custom type
type ACTIONTYPE = { type: "LOG_IN" } | { type: "LOG_OUT" };

// context reducer - gestisco le varie actions possibili
const reducer = (state: IDataContext["state"], action: ACTIONTYPE) => {
	switch (action.type) {
		case "LOG_IN":
			return {
				...state,
			};
		case "LOG_OUT":
			return {
				...state,
			};
		default:
			return state;
	}
};

// create context
const DataContext = createContext<IDataContext | null>(null);

// context hook
const useData = () => {
	const dataContext = useContext(DataContext);
	if (!dataContext) {
		throw new Error("useStore cannot be used outside of <StoreContext.Provider>");
	}
	return dataContext;
};

// context provider wrapper component - inizializzo il valore del context fornendo accesso al reducer
const DataProvider = ({ children }: { children: ReactNode }) => {
	// gestiamo lo state del context con l'hook useReducer
	const [state, dispatch] = useReducer(reducer, {});

	return <DataContext.Provider value={{ state, dispatch }}>{children}</DataContext.Provider>;
};

export { useData, DataProvider };
