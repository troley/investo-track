import ItemsButtonListItem from "./ItemsButtonListItem";

interface ItemsButtonList {
  data: { id: any; displayValue: any }[];
  itemClickHandler: (id: any) => void;
}

export default function ItemsButtonList({
  data,
  itemClickHandler,
}: ItemsButtonList) {
  return (
    <div className="join join-vertical max-h-60 overflow-auto">
      {data.map((d) => (
        <ItemsButtonListItem
          key={d.id}
          data={d}
          clickHandler={itemClickHandler}
        />
      ))}
    </div>
  );
}
