import { RequestFile } from '../api';
import { OrderItem } from './orderItem';

export class ExistingOrder {
    'email'?: string;
    'firstName'?: string;
    'id'?: string;
    'items'?: Array<OrderItem>;
    'lastName'?: string;

    static discriminator: string | undefined = undefined;

    static attributeTypeMap: Array<{name: string, baseName: string, type: string}> = [
        {
            "name": "email",
            "baseName": "email",
            "type": "string"
        },
        {
            "name": "firstName",
            "baseName": "firstName",
            "type": "string"
        },
        {
            "name": "id",
            "baseName": "id",
            "type": "string"
        },
        {
            "name": "items",
            "baseName": "items",
            "type": "Array<OrderItem>"
        },
        {
            "name": "lastName",
            "baseName": "lastName",
            "type": "string"
        }    ];

    static getAttributeTypeMap() {
        return ExistingOrder.attributeTypeMap;
    }
}

