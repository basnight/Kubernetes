import { RequestFile } from '../api';

export class OrderItem {
    'price'?: number;
    'productId'?: string;
    'quantity'?: number;

    static discriminator: string | undefined = undefined;

    static attributeTypeMap: Array<{name: string, baseName: string, type: string}> = [
        {
            "name": "price",
            "baseName": "price",
            "type": "number"
        },
        {
            "name": "productId",
            "baseName": "productId",
            "type": "string"
        },
        {
            "name": "quantity",
            "baseName": "quantity",
            "type": "number"
        }    ];

    static getAttributeTypeMap() {
        return OrderItem.attributeTypeMap;
    }
}

