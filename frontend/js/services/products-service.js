class Product{
    #id;
    #name;
    #owner;
    #bid;

    constructor(id, name, owner, bid){
        this.#id = id;
        this.#name = name;
        this.#owner = owner;
        this.#bid = bid;
    }

    get id() {return this.#id;};
    get name() {return this.#name;};
    get owner() {return this.#owner;};
    get bid() {return this.#bid;};
}

export class ProductsService{

    static async findAll() {
        const response = await fetch("http://127.0.0.1:26790/products");
        
        if(response.status === 200){
            let products = [];
            const data = await response.json();
            data.forEach((element) => {
                products.push(new Product(element["id"], element["name"], element["owner"], element["bid"]));
            });
            return products;
        }
        return null;
    }

    static async bid(productId){
        const response = await fetch(`http://127.0.0.1:26790/bid/${productId}`, {method: "POST"});
        if(response.status === 200){
            const data = await response.json();
            let product = new Product(data["id"], data["name"], data["owner"], data["bid"]);
            return product;
        }else{
            return null;
        }
    }

}