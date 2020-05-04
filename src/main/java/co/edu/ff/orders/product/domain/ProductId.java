package co.edu.ff.orders.product.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.product.serialization.LongSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductId implements LongSerializable {

    Long value;

    public ProductId(Long value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.longValue() >= 1l);
        this.value = value;
    }

    @Override
    public Long vaALong() {
        return value;
    }
}
