import { ProductsService } from "./services/products-service.js";
import { SSEClient } from "./libs/sse-client.js";
import { ProductsView } from "./views/products-view.js";

async function run(){
    let client = new SSEClient("127.0.0.1:26790");
    client.connect();
    let productView = new ProductsView();
    client.subscribe("bids", productView.updateBid);
    await productView.displayProducts();    
}

window.addEventListener("load", async (event) => {await run();});