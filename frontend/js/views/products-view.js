import { ProductsService } from "../services/products-service.js";

export class ProductsView{

    View(){

    }

    async displayProducts(){
        let products = await ProductsService.findAll();
        for(let i = 0; i < products.length; i++){   
            await this.#displayProduct(products[i]);
        }
    }

    async #displayProduct(product){
        let products = document.querySelector(".products");
        let productElement = document.createElement("div");
        
        productElement.dataset.id = product.id;
        
        productElement.classList.add("product");
        products.append(productElement);
        
        let productName = document.createElement("p");
        productName.classList.add("product-name");
        productName.innerHTML = product.name;
        productElement.append(productName);

        let productOwner = document.createElement("p");
        productOwner.classList.add("product-owner");
        productOwner.innerHTML = product.owner;
        productElement.append(productOwner);


        let productBid = document.createElement("p");
        productBid.classList.add("product-bid");
        productBid.innerHTML = product.bid;
        productElement.append(productBid);
        
        let bidButton = document.createElement("button");
        bidButton.innerHTML = "Enchérir";
        bidButton.addEventListener("click", async (event) => {
            let productId = productElement.dataset.id;
            
            let newProduct = await ProductsService.bid(productId);

            productBid.innerHTML = newProduct.bid;
        });
        bidButton.classList.add("product-bid-button");
        productElement.append(bidButton);
    }

    updateBid(data){
        let id = Number(data["id"]);
        let value = parseFloat(data["value"]);
        const element = document.querySelector("[data-id=\""+id+"\"");
        const elementBid = element.querySelector(".product-bid");
        elementBid.innerHTML = value;
    }
}