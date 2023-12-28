interface ItemsButtonListItem {
  data: { id: any; displayValue: any };
  clickHandler: (id: any) => void;
}

export default function ItemsButtonListItem({
  data,
  clickHandler,
}: ItemsButtonListItem) {
  return (
    <button
      className="btn join-item"
      onClick={() => clickHandler(data.id)}
    >
      {data.displayValue}
    </button>
  );
}
